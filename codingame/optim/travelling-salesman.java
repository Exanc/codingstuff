import java.util.*;
import java.io.*;
import java.math.*;


/**
 * Source: http://faculty.washington.edu/paymana/swarm/dorigo97-bs.pdf
 *         https://stackoverflow.com/a/16489590
 */
class Player {

    static final double a = 0.1;
    static final double B = 2;
    static final double q = 0.9;
    static double T0 = 1;
    static int N;

    static Random rng;

    static double[][] distanceLookup;
    static double[][] pheromoneLookup;

    static int[][] points;

    public static void main (String args[])
    {    
        /* Input */
        Scanner in = new Scanner(System.in);
        N = in.nextInt(); in.nextLine();

        pheromoneLookup = new double[N][N];
        distanceLookup = new double[N][N];
        points = new int[N][2];

        for (int i = 0; i < N; i++) {
            points[i][0] = in.nextInt();
            points[i][1] = in.nextInt();
            in.nextLine();
        }
        in.close();

        rng = new Random();

        /* Fill pheromone table with ones */
        for (int x = 0; x < N; x++) {
            for (int y = x; y < N; y++)
            {
                pheromoneLookup[x][y] = T0; pheromoneLookup[y][x] = T0;
        }   }

        /* Calculate distance lookup table */
        for (int a = 0; a < N; a++) {
            // Ingnore b = a case because distance is alwayse 0
            for (int b = a + 1; b < N; b++) {
                double dst = distance(
                    points[a][0], points[a][1], points[b][0], points[b][1]
                );
                distanceLookup[a][b] = dst;
                distanceLookup[b][a] = dst;
            }
        }

        T0 = N * heuristicTour();

        int antsN = 7;
        int iterations = 40;

        for (int i = 0; i < iterations; i++) {
            
            // Place ants
            Ant[] ants = new Ant[antsN];
            for (int j = 0; j < antsN; j++)
            {
                ants[j] = new Ant(rng.nextInt(65536) % N);
            }

            // Simulate
            for (int j = 0; j < N - 1; j++) {
                for (int k = 0; k < ants.length; k++)
                {
                    int next = antNextCity(ants[k]);
                    ants[k].distanceWalked += distanceLookup[ants[k].current][next];
                    ants[k].current = next;
                    ants[k].travelList.add(next);
                }
            }

            // Global pheromne spreading
            Ant shortest = ants[0];
            for (int j = 1; j < ants.length; j++)
            {
                if (ants[j].distanceWalked < shortest.distanceWalked)
                    shortest = ants[j];
            }
            updateGlobalPheromone(shortest);

        }

        // Do a final walk to find the path
        Ant ant = new Ant(0);
        for (int i = 0; i < N - 1; i++)
        {
            System.out.print(ant.current + " ");
            ant.current = antNextCity(ant);
            ant.travelList.add(ant.current);
        }
        System.out.print(ant.current + " ");
        System.out.println("0");
    }

    public static int antNextCity (Ant ant)
    {
        int cur = -1;

        double[] scores = new double[N];
        for (int i = 0; i < N; i++)
        {
            scores[i] = pheromoneLookup[ant.current][i] * Math.pow(heuristic(ant.current, i), B);
        }

        if (rng.nextDouble() < q)
        {   
            double max = -1;
            for (int i = 0; i < N; i++)
            {
                if (ant.travelList.contains(i))
                    continue;
                
                if (scores[i] > max) {
                    max = scores[i];
                    cur = i;
                }
            }
        }
        else
        {
            double[] probabilities = new double[N];
            double sum = 0;

            for (int i = 0; i < N; i++)
            {
                if (!ant.travelList.contains(i))
                    sum += scores[i];
            }

            if (ant.travelList.contains(0))
                probabilities[0] = 0;
            else
                probabilities[0] = scores[0] / sum;

            for (int i = 1; i < N; i++)
            {
                if (ant.travelList.contains(i))
                    probabilities[i] = probabilities[i - 1];
                else
                    probabilities[i] = probabilities[i - 1] + scores[i] / sum;
            }

            // Proportionnal distribution ?
            int i = 0;
            double p = rng.nextDouble() * probabilities[N - 1];
            p += Math.ulp(p);

            while (probabilities[i] < p)
                i++;
            cur = i;
        }

        updateLocalPheromone (ant.current, cur);

        return cur;
    }

    public static void updateGlobalPheromone  (Ant ant)
    {
        int last = ant.travelList.get(0);
        double deltaPhi = 1 / ant.distanceWalked;
        for (int j = 1; j <= N; j++)
        {
            int index  = j % N;
            double newAmmount = (1 - a)*pheromoneLookup[last][index] + a*deltaPhi;
            pheromoneLookup[last][index] = newAmmount;
            pheromoneLookup[index][last] = newAmmount;
        }
    }

    public static void updateLocalPheromone (int cityA, int cityB)
    {
        double newAmmount = (1 - a)*pheromoneLookup[cityA][cityB] + a*T0;
        pheromoneLookup[cityA][cityB] = newAmmount;
        pheromoneLookup[cityB][cityA] = newAmmount;
    }

    public static double heuristic (int cityA, int cityB)
    {
        return 1 / distanceLookup[cityA][cityB];
    }

    public static double distance (int xa, int ya, int xb, int yb)
    {
        int dx = xb - xa, dy = yb - ya;
        return Math.sqrt(dx*dx + dy*dy);
    }

    public static double heuristicTour ()
    {
        double traveled = 0;
        int cur = 0;
        ArrayList<Integer> path = new ArrayList<Integer>(N);

        for (int i = 0; i < N - 1; i++)
        {
            int next = -1;
            double min = Double.MAX_VALUE;

            for (int j = 0; j < N; j++)
            {
                if (!path.contains(i) && distanceLookup[cur][i] < min)
                {
                    min = distanceLookup[cur][i];
                    next = i;
                }
            }

            cur = next;
            path.add(next);
        }
        traveled += distanceLookup[cur][0];

        return 1 / traveled;
    }
}

class Ant {

    public int current;
    public double distanceWalked;
    public ArrayList<Integer> travelList;

    public Ant (int start) {

        this.current = start;
        this.distanceWalked = 0;
        this.travelList = new ArrayList<Integer>(Player.N);
        travelList.add(start);
    }
}
