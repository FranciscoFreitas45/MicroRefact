package switchtwentytwenty.project.Interface;
public interface MoneyValue {

   public boolean isPositiveOrZero();
   public int compare(MoneyValue moneyValue);
   public MoneyValue subtract(MoneyValue moneyValue);
   public MoneyValue sum(MoneyValue moneyValue);
}