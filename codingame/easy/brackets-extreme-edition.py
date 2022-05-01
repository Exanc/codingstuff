import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

s = input()

par = (s.count('(') == s.count(')') and s.find('(') <= s.find(')'))
cro = (s.count('[') == s.count(']') and s.find('[') <= s.find(']'))
acc = (s.count('{') == s.count('}') and s.find('{') <= s.find('}'))

print("true" if par and cro and acc else "false")