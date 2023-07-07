class PhysicalBook extends Book {
    private String dueDate;

    public PhysicalBook(String title, String author) {
        super(title, author);
        this.dueDate = null;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}