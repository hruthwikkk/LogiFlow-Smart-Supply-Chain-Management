import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

export const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const fetchShipments = () => api.get('/shipments');
export const fetchInventory = () => api.get('/inventory');
export const fetchAlerts = () => api.get('/alerts/recent');

export const updateShipmentStatus = (id: number, status: string) => 
  api.put(`/shipments/${id}/status`, status);

export const updateShipmentProgress = (id: number, progress: number) => 
  api.put(`/shipments/${id}/progress`, progress);

export const updateInventoryQuantity = (id: number, quantity: number) => 
  api.put(`/inventory/${id}/quantity`, quantity);