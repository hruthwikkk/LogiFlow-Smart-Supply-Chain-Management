import { useQuery } from 'react-query';
import { Package, AlertCircle, Loader2 } from 'lucide-react';
import { fetchInventory } from '../api/client';
import { DashboardCard } from './DashboardCard';
import type { InventoryItem } from '../types';

export function InventoryStatus() {
  const { data: inventory, isLoading, error } = useQuery<InventoryItem[]>(
    'inventory',
    async () => {
      const response = await fetchInventory();
      return response.data;
    }
  );

  if (isLoading) {
    return (
      <DashboardCard title="Inventory Status">
        <div className="flex justify-center items-center h-48">
          <Loader2 className="h-8 w-8 text-blue-500 animate-spin" />
        </div>
      </DashboardCard>
    );
  }

  if (error) {
    return (
      <DashboardCard title="Inventory Status">
        <div className="text-red-500 text-center">Failed to load inventory</div>
      </DashboardCard>
    );
  }

  return (
    <DashboardCard title="Inventory Status">
      <div className="space-y-4">
        {inventory?.map((item) => (
          <div key={item.id} className="flex items-center justify-between p-4 border rounded-lg">
            <div className="flex items-center gap-3">
              <div className={`p-2 rounded-lg ${
                item.status === 'CRITICAL'
                  ? 'bg-red-100'
                  : item.status === 'LOW'
                  ? 'bg-amber-100'
                  : 'bg-green-100'
              }`}>
                {item.status === 'CRITICAL' ? (
                  <AlertCircle className="text-red-600" size={20} />
                ) : (
                  <Package className={item.status === 'LOW' ? 'text-amber-600' : 'text-green-600'} size={20} />
                )}
              </div>
              <div>
                <h3 className="font-medium">{item.name}</h3>
                <p className="text-sm text-gray-600">
                  Quantity: {item.quantity} / Threshold: {item.threshold}
                </p>
              </div>
            </div>
            <span className={`px-3 py-1 rounded-full text-sm ${
              item.status === 'CRITICAL'
                ? 'bg-red-100 text-red-700'
                : item.status === 'LOW'
                ? 'bg-amber-100 text-amber-700'
                : 'bg-green-100 text-green-700'
            }`}>
              {item.status}
            </span>
          </div>
        ))}
      </div>
    </DashboardCard>
  );
}

export default InventoryStatus;