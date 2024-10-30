import { LayoutDashboard } from 'lucide-react';
import { QueryClient, QueryClientProvider } from 'react-query';
import { ShipmentTracker } from './components/ShipmentTracker';
import { InventoryStatus } from './components/InventoryStatus';
import { AlertsFeed } from './components/AlertsFeed';

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      refetchInterval: 30000, // Refetch every 30 seconds
      staleTime: 10000,
    },
  },
});

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <div className="min-h-screen bg-gray-50">
        <nav className="bg-white shadow-sm">
          <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
            <div className="flex items-center justify-between h-16">
              <div className="flex items-center">
                <LayoutDashboard className="h-8 w-8 text-blue-600" />
                <span className="ml-2 text-xl font-semibold text-gray-900">
                  Supply Chain Monitor
                </span>
              </div>
            </div>
          </div>
        </nav>

        <main className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
          <div className="grid grid-cols-1 lg:grid-cols-2 gap-8">
            <div className="space-y-8">
              <ShipmentTracker />
              <AlertsFeed />
            </div>
            <div>
              <InventoryStatus />
            </div>
          </div>
        </main>
      </div>
    </QueryClientProvider>
  );
}

export default App;