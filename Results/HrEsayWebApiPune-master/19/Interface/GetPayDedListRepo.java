public interface GetPayDedListRepo {

   public List<GetPayDedList> getPayDedList(int month,int year,List<Integer> empIds);
   public List<GetPayDedList> getBonusList(String date,List<Integer> empIds);
   public List<GetPayDedList> getLoanList(String date,List<Integer> empIds);
   public List<GetPayDedList> getPartialLoanList(int month,int year,List<Integer> empIds);
   public List<GetPayDedList> getEncashLeaveAmtList(int month,int year,List<Integer> empIds);
}