import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Compute the proper wall / jump point distances, according to the preprocessing phase of the JPS+ algorithm.
 **/
class Player {

	public static final int NORTH = 0,
                            EAST = 1,
                            SOUTH = 2,
                            WEST = 3,

                            NORTH_EAST = 4,
                            SOUTH_EAST = 5,
                            NORTH_WEST = 6,
                            SOUTH_WEST = 7;

    public static Terrain[][] terrain;
    public static int width;
    public static int height;

    /* Source : http://www.gameaipro.com/GameAIPro2/GameAIPro2_Chapter14_JPS_Plus_An_Extreme_A_Star_Speed_Optimization_for_Static_Uniform_Cost_Grids.pdf
     */
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        width = in.nextInt(); // Width of the map
        height = in.nextInt(); // Height of the map

                      terrain    = new Terrain[width][height];
        boolean[][][] jumpPoints = new boolean[width][height][4];
        int[][][]     distances  = new int[width][height][8];

        for (int y = 0; y < height; y++) {
            String row = in.next(); // A single row of the map consisting of passable terrain ('.') and walls ('#')
            for (int x = 0; x < width; x++) {
                
                char c = row.charAt(x);
                if (c == '#') {
                    terrain[x][y] = Terrain.Wall;
                } else {
                    terrain[x][y] = Terrain.Floor;
                }
            }
        }
        in.close();

        /* STEP 1: Identify all primary jump points by setting a directional flag in each node. */
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                
                if (terrain[x][y] == Terrain.Wall) continue;

                boolean bEast  = (x > 0),
                        bWest  = (x < width - 1),
                        bNorth = (y > 0),
                        bSouth = (y < height - 1);

                boolean tEast  = (bEast  && terrain[x - 1][y] == Terrain.Floor),
                        tWest  = (bWest  && terrain[x + 1][y] == Terrain.Floor),
                        tNorth = (bNorth && terrain[x][y - 1] == Terrain.Floor),
                        tSouth = (bSouth && terrain[x][y + 1] == Terrain.Floor);
                
                boolean tNE = (bNorth && bEast && terrain[x - 1][y - 1] == Terrain.Wall),
                        tSE = (bSouth && bEast && terrain[x - 1][y + 1] == Terrain.Wall),
                        tNW = (bNorth && bWest && terrain[x + 1][y - 1] == Terrain.Wall),
                        tSW = (bSouth && bWest && terrain[x + 1][y + 1] == Terrain.Wall);
                
                if (tEast) {
                    if (tNorth && tNE) jumpPoints[x][y][SOUTH] = true;
                    if (tSouth && tSE) jumpPoints[x][y][NORTH] = true;
                }

                if (tWest) {
                    if (tNorth && tNW) jumpPoints[x][y][SOUTH] = true;
                    if (tSouth && tSW) jumpPoints[x][y][NORTH] = true;
                }

                if (tSouth) {
                    if (tEast && tSE) jumpPoints[x][y][EAST] = true;
                    if (tWest && tSW) jumpPoints[x][y][WEST] = true;
                }

                if (tNorth) {
                    if (tEast && tNE) jumpPoints[x][y][EAST] = true;
                    if (tWest && tNW) jumpPoints[x][y][WEST] = true;
                }
            }
        }

        /* STEP 2 : Mark with distance all westward straight jump points and westward walls by 
         *          sweeping the map left to right. */

        for (int y = 0; y < height; y++) {

            int count = -1;
            boolean jumpPointLastSeen = false;

            for (int x = 0; x < width; x++) {

                if (terrain[x][y] == Terrain.Wall) {
                    count = -1;
                    jumpPointLastSeen = false;
                    distances[x][y][WEST] = 0;
                    continue;
                }
                count++;

                if (jumpPointLastSeen) {
                    distances[x][y][WEST] = count;

                } else { // Wall last seen
                    distances[x][y][WEST] = -count;
                }

                if (jumpPoints[x][y][WEST]) {
                    count = 0;
                    jumpPointLastSeen = true;
                }
            }
        }

        /* STEP 3 : Mark with distance all eastward straight jump points and eastward walls by
         *          sweeping the map right to left. */
        
        for (int y = 0; y < height; y++) {

            int count = -1;
            boolean jumpPointLastSeen = false;

            for (int x = width - 1; x >= 0; x--) {

                if (terrain[x][y] == Terrain.Wall) {
                    count = -1;
                    jumpPointLastSeen = false;
                    distances[x][y][EAST] = 0;
                    continue;
                }
                count++;

                if (jumpPointLastSeen) {
                    distances[x][y][EAST] = count;

                } else { // Wall last seen
                    distances[x][y][EAST] = -count;
                }

                if (jumpPoints[x][y][EAST]) {
                    count = 0;
                    jumpPointLastSeen = true;
                }
            }
        }

        /* STEP 4 : Mark with distance all northward straight jump points and northward walls by
                    sweeping the map up to down. */
        
        for (int x = 0; x < width; x++) {

            int count = -1;
            boolean jumpPointLastSeen = false;
            
            for (int y = 0; y < height; y++) {
            
                if (terrain[x][y] == Terrain.Wall) {
                    count = -1;
                    jumpPointLastSeen = false;
                    distances[x][y][NORTH] = 0;
                    continue;
                }
                count++;
            
                if (jumpPointLastSeen) {
                    distances[x][y][NORTH] = count;
            
                } else { // Wall last seen
                    distances[x][y][NORTH] = -count;
                }

                if (jumpPoints[x][y][NORTH]) {
                    count = 0;
                    jumpPointLastSeen = true;
                }
            }
        }

        /* STEP 5 : Mark with distance all southward straight jump points and southward walls by
                    sweeping the map down to up. */
        
        for (int x = 0; x < width; x++) {

            int count = -1;
            boolean jumpPointLastSeen = false;
                        
            for (int y = height - 1; y >= 0; y--) {
                        
                if (terrain[x][y] == Terrain.Wall) {
                    count = -1;
                    jumpPointLastSeen = false;
                    distances[x][y][SOUTH] = 0;
                    continue;
                }
                count++;
            
                if (jumpPointLastSeen) {
                    distances[x][y][SOUTH] = count;
            
                } else { // Wall last seen
                    distances[x][y][SOUTH] = -count;
                }
            
                if (jumpPoints[x][y][SOUTH]) {
                    count = 0;
                    jumpPointLastSeen = true;
                }
            }
        }

        /* STEP 6 : Mark with distance all southwest/southeast diagonal jump points and
         *          southwest/southeast walls by sweeping the map down to up. */

        /* SouthWest loop */
        for (int y = height - 1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                if (!isWall(x, y)) {

                    if ( isWall(x, y+1) || isWall(x-1, y) || isWall(x-1, y+1)) {
                        // Wall one away
                        distances[x][y][SOUTH_WEST] = 0;

                    } else if (!isWall(x, y+1) && !isWall(x-1, y)
                             && ( distances[x - 1][y + 1][SOUTH] > 0
                               || distances[x - 1][y + 1][WEST]  > 0)) {
                        // Straight jump point one away
                        distances[x][y][SOUTH_WEST] = 1;

                    } else {
                        // Increment from last
                        int jumpDistance = distances[x - 1][y + 1][SOUTH_WEST];

                        if (jumpDistance > 0) {
                            distances[x][y][SOUTH_WEST] = jumpDistance + 1;

                        } else {
                            distances[x][y][SOUTH_WEST] = jumpDistance - 1;
                        }
                    }
                }
            }
        }
        
        /* SouthEast loop */
        for (int y = height - 1; y >= 0; y--) {
            for (int x = width - 1; x >= 0; x--) {
                if (!isWall(x, y)) {

                    if (isWall(x, y+1) || isWall(x+1, y) || isWall(x+1, y+1)) {
                        // Wall one away
                        distances[x][y][SOUTH_EAST] = 0;

                    } else if (!isWall(x, y+1) && !isWall(x+1, y)
                             && ( distances[x + 1][y + 1][SOUTH] > 0
                               || distances[x + 1][y + 1][EAST]  > 0)) {
                        // Straight jump point one away
                        distances[x][y][SOUTH_EAST] = 1;

                    } else {
                        // Increment from last
                        int jumpDistance = distances[x + 1][y + 1][SOUTH_EAST];

                        if (jumpDistance > 0) {
                            distances[x][y][SOUTH_EAST] = jumpDistance + 1;

                        } else {
                            distances[x][y][SOUTH_EAST] = jumpDistance - 1;
                        }
                    }
                }
            }
        }

        /* STEP 7 : Mark with distance all northwest/northeast diagonal jump points and
         *          northwest/northeast walls by sweeping the map up to down. */
        
        /* NorthWest loop */
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (!isWall(x, y)) {

                    if ( isWall(x, y-1) || isWall(x-1, y) || isWall(x-1, y-1)) {
                        // Wall one away
                        distances[x][y][NORTH_WEST] = 0;

                    } else if (!isWall(x, y-1) && !isWall(x-1, y)
                             && ( distances[x - 1][y - 1][NORTH] > 0
                               || distances[x - 1][y - 1][WEST]  > 0)) {
                        // Straight jump point one away
                        distances[x][y][NORTH_WEST] = 1;

                    } else {
                        // Increment from last
                        int jumpDistance = distances[x - 1][y - 1][NORTH_WEST];

                        if (jumpDistance > 0) {
                            distances[x][y][NORTH_WEST] = jumpDistance + 1;

                        } else {
                            distances[x][y][NORTH_WEST] = jumpDistance - 1;
                        }
                    }
                }
            }
        }

        /* NorthEast loop */
        for (int y = 0; y < height; y++) {
            for (int x = width - 1; x >= 0; x--) {
                if (!isWall(x, y)) {

                    if ( isWall(x, y-1) || isWall(x+1, y) || isWall(x+1, y-1)) {
                        // Wall one away
                        distances[x][y][NORTH_EAST] = 0;

                    } else if (!isWall(x, y-1) && !isWall(x+1, y)
                             && ( distances[x + 1][y - 1][NORTH] > 0
                               || distances[x + 1][y - 1][EAST]  > 0)) {
                        // Straight jump point one away
                        distances[x][y][NORTH_EAST] = 1;

                    } else {
                        // Increment from last
                        int jumpDistance = distances[x + 1][y - 1][NORTH_EAST];

                        if (jumpDistance > 0) {
                            distances[x][y][NORTH_EAST] = jumpDistance + 1;

                        } else {
                            distances[x][y][NORTH_EAST] = jumpDistance - 1;
                        }
                    }
                }
            }
        }

        /* END RESULT */
        // For each empty tile of the map, a line containing "column row N NE E SE S SW W NW".
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                if (terrain[x][y] == Terrain.Wall) continue;

                int[] d = distances[x][y];

                System.out.println(x +" "+ y + " "
                                 + d[NORTH] +" "+ d[NORTH_EAST] +" "+ d[EAST] +" "+ d[SOUTH_EAST] + " "
                                 + d[SOUTH] +" "+ d[SOUTH_WEST] +" "+ d[WEST] +" "+ d[NORTH_WEST]);
            }
        }
    }

    public static boolean isWall(int x, int y) {

        if (x < 0 || y < 0 || x >= width || y >= height) {
            return true;
        }

        return terrain[x][y] == Terrain.Wall;
    }
}

enum Terrain { Floor, Wall; }