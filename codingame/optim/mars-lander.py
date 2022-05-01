import sys
import math

# ---------
# CONSTANTS
# ---------

gravity = 3.711

# ---------
# FUNCTIONS
# ---------

# Returns absolute value of n
def abs(n):
    return (n if n > 0 else -n)

# Return the altutide (relative to the surface) of a point
def altitude(_x, _y):

    p1 = {}
    p2 = {}
    i = 1

    for i in range(len(surface)):
        if _x >= surface[i-1]["x"] and _x <= surface[i]["x"]:
            p1 = surface[i-1]
            p2 = surface[i]
            break

    # Weird stuff (；⌣̀_⌣́)
    AAAA1 = {"x": _x, "y":  10000}
    AAAA2 = {"x": _x, "y": -10000}

    # Should get us a point of intersection between a vertical crossing the player and the surface (hopefully)
    p = get_line_intersection(AAAA1, AAAA2, p1, p2)

    if p["x"] is None:
        # Oops
        return 0
    
    return _y - p["y"]

# Return the sing of an number
def sign(n):
    if n < 0:
        return -1
    return 1

# ---- Finds the point of intersection of two segments with something called "Mathematics"
# source: https://qastack.fr/programming/563198/how-do-you-detect-where-two-line-segments-intersect
# function by "Gavin"
# modified from C to Python3 by myself
def get_line_intersection(p0, p1, p2, p3):

    s1_x = p1["x"] - p0["x"]; s1_y = p1["y"] - p0["y"]
    s2_x = p3["x"] - p2["x"]; s2_y = p3["y"] - p2["y"]

    s = (-s1_y * (p0["x"] - p2["x"]) + s1_x * (p0["y"] - p2["y"])) / (-s2_x * s1_y + s1_x * s2_y)
    t = ( s2_x * (p0["y"] - p2["y"]) - s2_y * (p0["x"] - p2["x"])) / (-s2_x * s1_y + s1_x * s2_y)

    if (s >= 0 and s <= 1 and t >= 0 and t <= 1):

        # Collision detected
        i_x = p0["x"] + (t * s1_x)
        i_y = p0["y"] + (t * s1_y)
        
        return {"x": i_x, "y": i_y}

    # No collision
    return {"x": None, "y": None}

# ------------------
# INITIAL PARAMETRES
# ------------------

n = int(input())
surface = [{}] * n
for i in range(n):
    _x, _y = [int(j) for j in input().split()]
    surface[i] = {"x": _x, "y": _y}

# Determine coordinates of the landing zone
i = 1
landing_x = 0
landing_y = 0
landing_width = 0
while i < len(surface):
    
    if surface[i]["y"] == surface[i-1]["y"]:
        x1 = surface[i-1]["x"]
        x2 = surface[i]["x"]

        landing_x = (x2 - x1)/2 + x1
        landing_y = surface[i]["y"]
        landing_width = (x2 - x1)/2.2
        break

    i += 1

# Gets the highest point of the map
i = 0
y_max = -1
while i < len(surface):

    if surface[i]["y"] > y_max:
        y_max = surface[i]["y"]

    i += 1

print(y_max, file=sys.stderr, flush=True)

# ---------
# GAME LOOP
# ---------
while True:
    # hs: the horizontal speed (in m/s), can be negative.
    # vs: the vertical speed (in m/s), can be negative.
    # f: the quantity of remaining fuel in liters.
    # r: the rotation angle in degrees (-90 to 90).
    # p: the thrust power (0 to 4).
    x, y, hs, vs, f, r, p = [int(i) for i in input().split()]

    power = 3
    angle = 15
    min_hs, max_hs = (0, 0)
    min_vs, max_vs = (0, 0)
    min_alt = 0

    landing_dist = landing_x - x
    landing_dir = -sign(landing_dist)
    angle_factor = 0

    if landing_dist < 0:
        angle_factor = 1
    else:
        angle_factor = -1

    # --- If the capsule is within the "landing zone"
    if abs(landing_dist) < landing_width:
        min_hs, max_hs = (0, 5)

        if landing_dir == 1:
            min_hs, max_hs = (-5, 0)

        min_vs, max_vs = (-39, 0)
        min_alt = 0

    else:
        min_hs, max_hs = (20, 25)

        if landing_dir == 1:
            min_hs, max_hs = (-25, -20)

        min_vs, max_vs = (-39, 0)
        min_alt = y_max + 500

    # --- Keep horizontal speed in range(min_hs, max_hs)
    if landing_dir == -1:
        if hs > max_hs:
            angle = -35
            power = 4

        elif hs < min_hs:
            angle = 35
            power = 3

        else:
            angle = 0
    
    else:
        if hs < min_hs:
            angle = -35
            power = 4

            if hs < -50:
                angle = -45

        elif hs > max_hs:
            angle = 35
            power = 3

        else:
            angle = 0
    
    # --- Keep vectical speed in range(min_vs, max_vs)
    if vs < min_vs:
        power = 4

    elif vs > max_vs:
        power = 0

    # --- Break if min altitude is reached
    if y < min_alt:
        angle /= 1.5
        power = 4
    
    if abs(landing_dist) < landing_width and altitude(x, y) < 15*abs(vs):
        angle = 0

    # To debug: print("Debug messages...", file=sys.stderr, flush=True)
    # R P. R is the desired rotation angle. P is the desired thrust power.
    print(round(angle*angle_factor), power, sep=" ")
