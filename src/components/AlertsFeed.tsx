import { useQuery } from 'react-query';
import { AlertCircle, AlertTriangle, Info, Loader2 } from 'lucide-react';
import { fetchAlerts } from '../api/client';
import { DashboardCard } from './DashboardCard';
import type { Alert } from '../types';

export function AlertsFeed() {
  const { data: alerts, isLoading, error } = useQuery<Alert[]>(
    'alerts',
    async () => {
      const response = await fetchAlerts();
      return response.data;
    }
  );

  if (isLoading) {
    return (
      <DashboardCard title="Recent Alerts">
        <div className="flex justify-center items-center h-48">
          <Loader2 className="h-8 w-8 text-blue-500 animate-spin" />
        </div>
      </DashboardCard>
    );
  }

  if (error) {
    return (
      <DashboardCard title="Recent Alerts">
        <div className="text-red-500 text-center">Failed to load alerts</div>
      </DashboardCard>
    );
  }

  return (
    <DashboardCard title="Recent Alerts">
      <div className="space-y-3">
        {alerts?.map((alert) => (
          <div
            key={alert.id}
            className={`flex items-center gap-3 p-3 rounded-lg ${
              alert.type === 'ERROR'
                ? 'bg-red-50'
                : alert.type === 'WARNING'
                ? 'bg-amber-50'
                : 'bg-blue-50'
            }`}
          >
            {alert.type === 'ERROR' ? (
              <AlertCircle className="text-red-500" size={20} />
            ) : alert.type === 'WARNING' ? (
              <AlertTriangle className="text-amber-500" size={20} />
            ) : (
              <Info className="text-blue-500" size={20} />
            )}
            <div className="flex-1">
              <p className={`text-sm ${
                alert.type === 'ERROR'
                  ? 'text-red-700'
                  : alert.type === 'WARNING'
                  ? 'text-amber-700'
                  : 'text-blue-700'
              }`}>
                {alert.message}
              </p>
              <p className="text-xs text-gray-500 mt-1">
                {new Date(alert.timestamp).toLocaleTimeString()}
              </p>
            </div>
          </div>
        ))}
      </div>
    </DashboardCard>
  );
}

export default AlertsFeed;