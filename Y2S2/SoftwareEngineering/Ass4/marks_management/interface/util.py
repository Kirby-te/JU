import sys
sys.path.append("../marks_management")

from tkinter import *
import re

bg_color = '#A020F0'
image_width = 40
image_height = 50

def check_email(email: str) -> bool:
    pattern = r"^[a-z](\.[a-z0-9]+)*@([a-z0-9]+)+(\.[a-z]{2,4})$"
    
    match = re.match(pattern=pattern, string=email)
    
    return bool(match)