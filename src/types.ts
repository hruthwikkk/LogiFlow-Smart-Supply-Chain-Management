export interface ShipmentStatus {
  id: string;
  origin: string;
  destination: string;
  status: 'IN_TRANSIT' | 'DELIVERED' | 'DELAYED';
  eta: string;
  progress: number;
}

export interface InventoryItem {
  id: string;
  name: string;
  quantity: number;
  threshold: number;
  status: 'NORMAL' | 'LOW' | 'CRITICAL';
}

export interface Alert {
  id: string;
  type: 'WARNING' | 'ERROR' | 'INFO';
  message: string;
  timestamp: string;
}