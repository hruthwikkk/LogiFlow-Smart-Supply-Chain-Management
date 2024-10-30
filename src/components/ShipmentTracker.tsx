import { useQuery } from 'react-query';
import { Truck, Package, AlertTriangle, Loader2 } from 'lucide-react';
import { fetchShipments } from '../api/client';
import { DashboardCard } from './DashboardCard';
import type { ShipmentStatus } from '../types';

export function ShipmentTracker() {
  const { data: shipments, isLoading, error } = useQuery<ShipmentStatus[]>(
    'shipments',
    async () => {
      const response = await fetchShipments();
      return response.data;
    }
  );

  if (isLoading) {
    return (
      <DashboardCard title="Active Shipments">
        <div className="flex justify-center items-center h-48">
          <Loader2 className="h-8 w-8 text-blue-500 animate-spin" />
        </div>
      </DashboardCard>
    );
  }

  if (error) {
    return (
      <DashboardCard title="Active Shipments">
        <div className="text-red-500 text-center">Failed to load shipments</div>
      </DashboardCard>
    );
  }

  return (
    <DashboardCard title="Active Shipments">
      <div className="space-y-4">
        {shipments?.map((shipment) => (
          <div key={shipment.id} className="border rounded-lg p-4">
            <div className="flex items-center justify-between mb-2">
              <div className="flex items-center gap-2">
                {shipment.status === 'DELAYED' ? (
                  <AlertTriangle className="text-amber-500" size={20} />
                ) : shipment.status === 'DELIVERED' ? (
                  <Package className="text-green-500" size={20} />
                ) : (
                  <Truck className="text-blue-500" size={20} />
                )}
                <span className="font-medium">
                  {shipment.origin} → {shipment.destination}
                </span>
              </div>
              <span className={`text-sm px-2 py-1 rounded-full ${
                shipment.status === 'DELAYED'
                  ? 'bg-amber-100 text-amber-700'
                  : shipment.status === 'DELIVERED'
                  ? 'bg-green-100 text-green-700'
                  : 'bg-blue-100 text-blue-700'
              }`}>
                {shipment.status.replace('_', ' ')}
              </span>
            </div>
            <div className="w-full bg-gray-200 rounded-full h-2">
              <div
                className={`h-2 rounded-full ${
                  shipment.status === 'DELAYED'
                    ? 'bg-amber-500'
                    : shipment.status === 'DELIVERED'
                    ? 'bg-green-500'
                    : 'bg-blue-500'
                }`}
                style={{ width: `${shipment.progress}%` }}
              />
            </div>
            <div className="mt-2 text-sm text-gray-600">
              ETA: {new Date(shipment.eta).toLocaleDateString()}
            </div>
          </div>
        ))}
      </div>
    </DashboardCard>
  );
}

export default ShipmentTracker;