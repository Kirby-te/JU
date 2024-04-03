from util import *

def conformation_box(root: Tk, message: str):
    
    answer = BooleanVar()
    answer.set(False)
    
    def action(ans):
        answer.set(ans)
        conformation_box_fr.destroy()
        root.update()
    
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