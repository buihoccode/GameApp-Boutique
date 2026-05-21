import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { z } from 'zod';
import { useCreateJeu, useUpdateJeu } from '../hooks/useJeux';

// Le schéma de validation
const jeuSchema = z.object({
  titre: z.string().min(2, 'Min 2 caractères').max(100),
  prix: z.number().positive('Le prix doit être positif'),
});
type JeuFormData = z.infer<typeof jeuSchema>;

interface Props {
  jeu?: any; // Si fourni, on est en mode édition
  onSuccess?: () => void;
  onCancel?: () => void;
}

export function JeuForm({ jeu, onSuccess, onCancel }: Props) {
  const createMut = useCreateJeu();
  const updateMut = useUpdateJeu();
  const isEditing = !!jeu;

  const { register, handleSubmit, formState: { errors } } = useForm<JeuFormData>({
    resolver: zodResolver(jeuSchema),
    defaultValues: jeu ? { titre: jeu.titre, prix: jeu.prix } : { titre: '', prix: 0 },
  });

  const onSubmit = async (data: JeuFormData) => {
    if (isEditing && jeu) {
      await updateMut.mutateAsync({ id: jeu.id, data });
    } else {
      await createMut.mutateAsync(data);
    }
    onSuccess?.(); // Ferme la modale
  };

  const isPending = createMut.isPending || updateMut.isPending;

  return (
    <div className="bg-white p-6 rounded-xl shadow-lg max-w-md w-full">
      <h2 className="text-xl font-bold mb-4">{isEditing ? 'Modifier le jeu' : 'Nouveau jeu'}</h2>
      
      <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
        <div>
          <label className="block text-sm font-medium mb-1">Titre du jeu</label>
          <input {...register('titre')} className="w-full border rounded-lg px-3 py-2" />
          {errors.titre && <p className="text-red-500 text-sm mt-1">{errors.titre.message}</p>}
        </div>

        <div>
          <label className="block text-sm font-medium mb-1">Prix (€)</label>
          <input type="number" step="0.01" {...register('prix', { valueAsNumber: true })} className="w-full border rounded-lg px-3 py-2" />
          {errors.prix && <p className="text-red-500 text-sm mt-1">{errors.prix.message}</p>}
        </div>

        <div className="flex gap-3 pt-4">
          <button type="submit" disabled={isPending} className="flex-1 bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 disabled:opacity-50">
            {isPending ? 'En cours...' : (isEditing ? 'Sauvegarder' : 'Créer')}
          </button>
          {onCancel && (
            <button type="button" onClick={onCancel} className="px-4 py-2 border rounded-lg hover:bg-gray-50">
              Annuler
            </button>
          )}
        </div>
      </form>
    </div>
  );
}