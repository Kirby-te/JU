from util import *
from welcomePage import welcome_page 

root = Tk()
root.title("Marks Management System")
# root.iconbitmap(logo_icon)
root.geometry("500x600")

welcome_page(root)

root.mainloop()