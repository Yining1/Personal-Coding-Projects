import tkinter as tk

class Editor(tk.Text):
    def __init__(self, master=None, xscrollcommand=, yscrollcommand=yscrollcommand):
        super().__init__(master)
        self.pack()

root = tk.Tk()
app = Editor(master=root)
app.mainloop()