import sys
sys.path.append("../marks_management")

from tkinter import *
from login import LoginPage


root = Tk()
root.title("Marks Management System")
# root.iconbitmap(logo_icon)
root.geometry("500x600")

bg_color = '#A020F0'
image_width = 40
image_height = 50

# loading images
logo_icon = PhotoImage(file='assets/logo.png')
student_icon = PhotoImage(file='assets/student_icon.png')
teacher_icon = PhotoImage(file='assets/teacher_icon.png')
admin_icon = PhotoImage(file='assets/admin_icon.png')
register_icon = PhotoImage(file='assets/register_icon.png')

def welcome_page():
    
    def formard_to_admin_login():
        welcome_page_fr.destroy()
        admin_login_page()
    
    def formard_to_teacher_login():
        welcome_page_fr.destroy()
        root.update()
        teacher_login_page()
        
    def formard_to_student_login():
        welcome_page_fr.destroy()
        student_login_page()
        
    def formard_to_register_page():
        welcome_page_fr.destroy()
        registration_page()
        
    welcome_page_fr = Frame(root, highlightbackground=bg_color, highlightthickness=3)


    heading_lb = Label(welcome_page_fr, text='Login to Continue', bg=bg_color, 
                    fg='white', font=('Bold', 18))
    heading_lb.place(x=0, y=0, width=454)


    admin_login_btn = Button(welcome_page_fr, text='Admin', bg=bg_color, 
                        fg='white', font=('Bold', 15), bd=0,
                            command=formard_to_admin_login)
    admin_login_btn.place(x=140, y=125, width=210)


    student_login_btn = Button(welcome_page_fr, text='Student', bg=bg_color, 
                            fg='white', font=('Bold', 15), bd=0,
                            command=formard_to_student_login)
    student_login_btn.place(x=140, y=185, width=210)

    # student_login_image = Button(welcome_page_fr, image=student_icon, bd=0)
    # student_login_image.place(x=80, y=100)


    teacher_login_btn = Button(welcome_page_fr, text='Teacher', bg=bg_color, 
                            fg='white', font=('Bold', 15), bd=0,
                            command=formard_to_teacher_login)
    teacher_login_btn.place(x=140, y=245, width=210)


    register_login_btn = Button(welcome_page_fr, text='Create Account', bg=bg_color, 
                            fg='white', font=('Bold', 15), bd=0,
                            command=formard_to_register_page)
    register_login_btn.place(x=140, y=305, width=210)
    

    welcome_page_fr.pack(pady=30)
    welcome_page_fr.pack_propagate(False)
    welcome_page_fr.configure(width=460, height=552)


def student_login_page():
    
    def formard_to_welcome_page():
        student_login_page_fr.destroy()
        welcome_page()
        
    student_login_page_fr = Frame(root, highlightbackground=bg_color, highlightthickness=3)

    heading_lb = Label(student_login_page_fr, text='Student Login', bg=bg_color, 
                    fg='white', font=('Bold', 18))
    heading_lb.place(x=0, y=0, width=454)
    
    back_btn = Button(student_login_page_fr, text='back', bg=bg_color,
                      fg='white', font=('Bold', 18), command=formard_to_welcome_page)
    back_btn.place(x=10, y=40)

    id_number_lb = Label(student_login_page_fr, text='Enter Roll No or Email Id', font=('Bold', 15), fg=bg_color)
    id_number_lb.place(x=100, y=140)
    id_number_ent = Entry(student_login_page_fr, font=('Bold', 15),
                        justify=CENTER, highlightcolor=bg_color,
                        highlightbackground='grey', highlightthickness=2)
    id_number_ent.place(x=100, y=180)

    password_lb = Label(student_login_page_fr, text='Enter Password', font=('Bold', 15), fg=bg_color)
    password_lb.place(x=100, y=230)
    password_ent = Entry(student_login_page_fr, font=('Bold', 15),
                        justify=CENTER, highlightcolor=bg_color,
                        highlightbackground='grey', highlightthickness=2)
    password_ent.place(x=100, y=270)

    login_btn = Button(student_login_page_fr, text='Login', font=('Bold', 15),
                    bg=bg_color, fg='white')
    login_btn.place(x=180, y=320, height=40)


    student_login_page_fr.pack(pady=30)
    student_login_page_fr.pack_propagate(False)
    student_login_page_fr.configure(width=460, height=552)

def admin_login_page():
    
    def formard_to_welcome_page():
        admin_login_page_fr.destroy()
        welcome_page()

    admin_login_page_fr = Frame(root, highlightbackground=bg_color, highlightthickness=3)

    heading_lb = Label(admin_login_page_fr, text='Admin Login', bg=bg_color, 
                    fg='white', font=('Bold', 18))
    heading_lb.place(x=0, y=0, width=454)
    
    back_btn = Button(admin_login_page_fr, text='back', bg=bg_color,
                      fg='white', font=('Bold', 18), command=formard_to_welcome_page)
    back_btn.place(x=10, y=40)

    id_number_lb = Label(admin_login_page_fr, text='Enter Id or Email', font=('Bold', 15), fg=bg_color)
    id_number_lb.place(x=100, y=140)
    id_number_ent = Entry(admin_login_page_fr, font=('Bold', 15),
                        justify=CENTER, highlightcolor=bg_color,
                        highlightbackground='grey', highlightthickness=2)
    id_number_ent.place(x=100, y=180)

    password_lb = Label(admin_login_page_fr, text='Enter Password', font=('Bold', 15), fg=bg_color)
    password_lb.place(x=100, y=230)
    password_ent = Entry(admin_login_page_fr, font=('Bold', 15),
                        justify=CENTER, highlightcolor=bg_color,
                        highlightbackground='grey', highlightthickness=2)
    password_ent.place(x=100, y=270)

    login_btn = Button(admin_login_page_fr, text='Login', font=('Bold', 15),
                    bg=bg_color, fg='white')
    login_btn.place(x=180, y=320, height=40)


    admin_login_page_fr.pack(pady=30)
    admin_login_page_fr.pack_propagate(False)
    admin_login_page_fr.configure(width=460, height=552)

def teacher_login_page():
    
    def formard_to_welcome_page():
        teacher_login_page_fr.destroy()
        welcome_page()

    teacher_login_page_fr = Frame(root, highlightbackground=bg_color, highlightthickness=3)

    heading_lb = Label(teacher_login_page_fr, text='Teacher Login', bg=bg_color, 
                    fg='white', font=('Bold', 18))
    heading_lb.place(x=0, y=0, width=454)
    
    back_btn = Button(teacher_login_page_fr, text='back', bg=bg_color,
                      fg='white', font=('Bold', 18), command=formard_to_welcome_page)
    back_btn.place(x=10, y=40)

    id_number_lb = Label(teacher_login_page_fr, text='Enter Id or Email', font=('Bold', 15), fg=bg_color)
    id_number_lb.place(x=100, y=140)
    id_number_ent = Entry(teacher_login_page_fr, font=('Bold', 15),
                        justify=CENTER, highlightcolor=bg_color,
                        highlightbackground='grey', highlightthickness=2)
    id_number_ent.place(x=100, y=180)

    password_lb = Label(teacher_login_page_fr, text='Enter Password', font=('Bold', 15), fg=bg_color)
    password_lb.place(x=100, y=230)
    password_ent = Entry(teacher_login_page_fr, font=('Bold', 15),
                        justify=CENTER, highlightcolor=bg_color,
                        highlightbackground='grey', highlightthickness=2)
    password_ent.place(x=100, y=270)

    login_btn = Button(teacher_login_page_fr, text='Login', font=('Bold', 15),
                    bg=bg_color, fg='white')
    login_btn.place(x=180, y=320, height=40)


    teacher_login_page_fr.pack(pady=30)
    teacher_login_page_fr.pack_propagate(False)
    teacher_login_page_fr.configure(width=460, height=552)


def registration_page():
    
    def formard_to_welcome_page():
        ans = conformation_box(message='Leave Registration Form?')
        if ans:
            registration_page_fr.destroy()
            root.update()
            welcome_page()
    
    def check_input_validation():
        if email_ent.get() == '':
            # email_ent.config(highlightcolor='red', highlightbackground='red')
            email_ent.focus()
            message_box(message='Email Required')
            return
        if password_ent.get() == '':
            # password_ent.config(highlightcolor='red', highlightbackground='red')
            password_ent.focus()
            message_box(message='Password Required')
            return
        if first_name_ent.get() == '':
            # first_name_ent.config(highlightcolor='red', highlightbackground='red')
            first_name_ent.focus()
            message_box(message='Name Required')
            return
        if phone_number_ent.get() == '':
            # phone_number_ent.config(highlightcolor='red', highlightbackground='red')
            phone_number_ent.focus()
            message_box(message='Phone Number Required')
            return
        if user_type_var.get() == '':
            # user_type_radio_button1.config(highlightcolor='red', highlightbackground='red')
            user_type_radio_button1.focus()
            message_box(message='Slecet User Type')
            return
        
        
    def register():
        # Implement registration logic here
        # Retrieve data from entry fields and radio buttons
        email = email_ent.get()
        password = password_ent.get()
        first_name = first_name_ent.get()
        last_name = last_name_ent.get()
        phone_number = phone_number_ent.get()
        # user_type = user_type_var.get()
        print("Email:", email)
        print("Password:", password)
        print("First Name:", first_name)
        print("Last Name:", last_name)
        print("Phone Number:", phone_number)
        # print("User Type:", user_type)
        
    user_type_var = StringVar()
        
    registration_page_fr = Frame(root, highlightbackground=bg_color, highlightthickness=3)

    heading_lb = Label(registration_page_fr, text='Create Account', bg=bg_color, 
                    fg='white', font=('Bold', 18))
    heading_lb.place(x=0, y=0, width=454)
    
    back_btn = Button(registration_page_fr, text='back', bg=bg_color,
                      fg='white', font=('Bold', 18), command=formard_to_welcome_page)
    back_btn.place(x=10, y=40)
    
    ## student or teacher
    user_type_radio_button1 = Radiobutton(registration_page_fr, text='Student', font=('Bold', 12),
                                          variable=user_type_var, value='student')
    user_type_radio_button1.place(x = 100, y = 70)
    user_type_radio_button2 = Radiobutton(registration_page_fr, text='Teacher', font=('Bold', 12),
                                          variable=user_type_var, value='teacher')
    user_type_radio_button2.place(x = 200, y = 70)
    user_type_var.set('student')
  
    ent_gap = 30
    field_gap = 70
    start = 100
    
    email_lb = Label(registration_page_fr, text='Email', font=('Bold', 15), fg=bg_color)
    email_lb.place(x=100, y=start)
    email_ent = Entry(registration_page_fr, font=('Bold', 15),
                      highlightcolor=bg_color,
                      highlightbackground='grey', highlightthickness=2)
    email_ent.place(x=100, y=(start + ent_gap))

    password_lb = Label(registration_page_fr, text='Password', font=('Bold', 15), fg=bg_color)
    password_lb.place(x=100, y=(start + field_gap))
    password_ent = Entry(registration_page_fr, font=('Bold', 15),
                         show='*', highlightcolor=bg_color,
                         highlightbackground='grey', highlightthickness=2)
    password_ent.place(x=100, y=(start + field_gap )+ ent_gap)

    first_name_lb = Label(registration_page_fr, text='First Name', font=('Bold', 15), fg=bg_color)
    first_name_lb.place(x=100, y=(start + field_gap * 2))
    first_name_ent = Entry(registration_page_fr, font=('Bold', 15),
                           highlightcolor=bg_color,
                           highlightbackground='grey', highlightthickness=2)
    first_name_ent.place(x=100, y=(start + field_gap*2 + ent_gap))

    last_name_lb = Label(registration_page_fr, text='Last Name', font=('Bold', 15), fg=bg_color)
    last_name_lb.place(x=100, y=(start + field_gap*3))
    last_name_ent = Entry(registration_page_fr, font=('Bold', 15),
                          highlightcolor=bg_color,
                          highlightbackground='grey', highlightthickness=2)
    last_name_ent.place(x=100, y=(start + field_gap*3 + ent_gap))

    phone_number_lb = Label(registration_page_fr, text='Phone Number', font=('Bold', 15), fg=bg_color)
    phone_number_lb.place(x=100, y=(start + field_gap*4))
    phone_number_ent = Entry(registration_page_fr, font=('Bold', 15),
                          highlightcolor=bg_color,
                          highlightbackground='grey', highlightthickness=2)
    phone_number_ent.place(x=100, y=(start + field_gap*4 + ent_gap))

    submit_btn = Button(registration_page_fr, text='Submit', font=('Bold', 15),
                    bg=bg_color, fg='white', command=check_input_validation)
    submit_btn.place(x=180, y=(start + field_gap*5), height=40)


    registration_page_fr.pack(pady=30)
    registration_page_fr.pack_propagate(False)
    registration_page_fr.configure(width=460, height=552)
    
def conformation_box(message):
    
    answer = BooleanVar()
    answer.set(False)
    
    def action(ans):
        answer.set(ans)
        conformation_box_fr.destroy()
    
    conformation_box_fr = Frame(root, highlightbackground=bg_color,
                                highlightthickness=3)
    message_lb = Label(conformation_box_fr, text=message, font=('Bold', 15),
                       justify=CENTER)
    message_lb.pack(pady=20)
    
    cancel_btn = Button(conformation_box_fr, text="Cancel", font=('Bold',15),
                        bd=0, bg=bg_color, fg='white',
                        command=lambda: action(False))
    cancel_btn.place(x=50, y=160)
    
    yes_btn = Button(conformation_box_fr, text="Yes", font=('Bold',15),
                        bd=0, bg=bg_color, fg='white',
                        command=lambda: action(True))
    yes_btn.place(x=190, y=160)
    
    conformation_box_fr.place(x=100, y=120, width=320, height=220)
    
    root.wait_window(conformation_box_fr)
    return answer.get()

def message_box(message):
    message_box_fr = Frame(root, highlightbackground=bg_color,
                                highlightthickness=3)
    message_lb = Label(message_box_fr, text=message, font=('Bold', 15),
                       justify=CENTER)
    message_lb.pack(pady=50)
    
    close_btn = Button(message_box_fr, text="X", font=('Bold',13),
                        bd=0, fg=bg_color, command=lambda: message_box_fr.destroy())
    close_btn.place(x=270, y=5)
    
    message_box_fr.place(x=100, y=120, width=320, height=220)
    
    root.wait_window(message_box_fr)

welcome_page()
root.mainloop()