import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { api } from '@/shared/lib/axios';
const JEUX_KEY = ['jeux'];

//LIRE la liste paginée
export function useJeux(page = 0, size = 10) {
  return useQuery({
    queryKey: [...JEUX_KEY, page, size],
    queryFn: async () => {
      const response = await api.get(`/api/jeux?page=${page}&size=${size}`);
      return response.data.data; // On retourne le PageDTO
    },
  });
}

//LIRE un seul jeu
export function useJeu(id: number) {
  return useQuery({
    queryKey: [...JEUX_KEY, id],
    queryFn: async () => {
      const response = await api.get(`/api/jeux/${id}`);
      return response.data.data;
    },
    enabled: id > 0,
  });
}

//CRÉER
export function useCreateJeu() {
  const qc = useQueryClient();
  return useMutation({
    mutationFn: async (data: any) => await api.post('/api/jeux', data),
    onSuccess: () => {
      qc.invalidateQueries({ queryKey: JEUX_KEY }); // Rafraîchit la liste
    },
  });
}

//MODIFIER
export function useUpdateJeu() {
  const qc = useQueryClient();
  return useMutation({
    mutationFn: async ({ id, data }: { id: number; data: any }) => 
      await api.put(`/api/jeux/${id}`, data),
    onSuccess: () => {
      qc.invalidateQueries({ queryKey: JEUX_KEY }); // Rafraîchit la liste
    },
  });
}

//SUPPRIMER
export function useDeleteJeu() {
  const qc = useQueryClient();
  return useMutation({
    mutationFn: async (id: number) => await api.delete(`/api/jeux/${id}`),
    onSuccess: () => {
      qc.invalidateQueries({ queryKey: JEUX_KEY }); // Rafraîchit la liste
    },
  });
}