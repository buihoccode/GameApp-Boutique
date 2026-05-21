import { useState } from 'react';
import { useJeux, useDeleteJeu } from '../hooks/useJeux';
import { JeuForm } from './JeuForm';

export function JeuList() {
  const [page, setPage] = useState(0);
  const [showForm, setShowForm] = useState(false);
  const [jeuToEdit, setJeuToEdit] = useState<any>(null);

  const { data, isLoading, error } = useJeux(page, 10);
  const deleteMutation = useDeleteJeu();

  // Gestion des états de chargement et d'erreur
  if (isLoading) return <div className="p-8 text-center">Chargement des jeux...</div>;
  if (error) return <div className="p-4 bg-red-50 text-red-600 rounded">Erreur : {error.message}</div>;

  const handleAdd = () => {
    setJeuToEdit(null);
    setShowForm(true);
  };

  const handleEdit = (jeu: any) => {
    setJeuToEdit(jeu);
    setShowForm(true);
  };

  const closeForm = () => {
    setShowForm(false);
    setJeuToEdit(null);
  };

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold">Catalogue des Jeux</h1>
        <button onClick={handleAdd} className="bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700">
          + Nouveau Jeu
        </button>
      </div>

      {/* La Modale */}
      {showForm && (
        <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
          <JeuForm jeu={jeuToEdit} onSuccess={closeForm} onCancel={closeForm} />
        </div>
      )}

      {/* Le Tableau */}
      <div className="bg-white rounded-xl shadow overflow-hidden">
        <table className="w-full">
          <thead className="bg-gray-50">
            <tr>
              <th className="text-left px-6 py-3 font-medium text-gray-500">Titre</th>
              <th className="text-left px-6 py-3 font-medium text-gray-500">Prix</th>
              <th className="text-right px-6 py-3 font-medium text-gray-500">Actions</th>
            </tr>
          </thead>
          <tbody className="divide-y divide-gray-100">
            {data?.content.map((jeu: any) => (
              <tr key={jeu.id} className="hover:bg-gray-50">
                <td className="px-6 py-4">{jeu.titre}</td>
                <td className="px-6 py-4">{jeu.prix.toFixed(2)} €</td>
                <td className="px-6 py-4 text-right flex justify-end gap-2">
                  <button onClick={() => handleEdit(jeu)} className="text-blue-600 hover:text-blue-800">Éditer</button>
                  <button onClick={() => deleteMutation.mutate(jeu.id)} className="text-red-600 hover:text-red-800 ml-4">Supprimer</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* La Pagination */}
      <div className="flex justify-between items-center mt-4">
        <span className="text-sm text-gray-500">
          {data?.totalElements} jeu(x) — Page {(data?.page ?? 0) + 1}/{data?.totalPages}
        </span>
        <div className="flex gap-2">
          <button 
            onClick={() => setPage((p) => Math.max(0, p - 1))} 
            disabled={data?.first} 
            className="px-3 py-1 border rounded disabled:opacity-50"
          >
            Précédent
          </button>
          <button 
            onClick={() => setPage((p) => p + 1)} 
            disabled={data?.last} 
            className="px-3 py-1 border rounded disabled:opacity-50"
          >
            Suivant
          </button>
        </div>
      </div>
    </div>
  );
}