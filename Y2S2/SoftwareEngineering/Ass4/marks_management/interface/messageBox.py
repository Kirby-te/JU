from util import *

def message_box(root: Tk, message: str):
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