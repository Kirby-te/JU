from util import *
from welcomePage import welcome_page 

root = Tk()
root.title("Marks Management System")
# root.iconbitmap(logo_icon)
root.geometry("500x600")

# loading images
# logo_icon = PhotoImage(file='assets/logo.png')
# student_icon = PhotoImage(file='assets/student_icon.png')
# teacher_icon = PhotoImage(file='assets/teacher_icon.png')
# admin_icon = PhotoImage(file='assets/admin_icon.png')
# register_icon = PhotoImage(file='assets/register_icon.png')

welcome_page(root)

root.mainloop()