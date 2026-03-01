interface FinanceTools {
    void addIncome(double amt, String note);
    void addExpense(double amt, String note);
}

interface MinutesTools {
    void addMinutes(String text);
}

interface EventTools {
    void createEvent(String name, double budget);
    int getEventsCount();
}
