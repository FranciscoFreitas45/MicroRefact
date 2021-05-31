public interface GetClaimApplyAuthwiseRepo {

   public List<GetClaimApplyAuthwise> getClaimApplyList(int empId);
   public List<GetClaimApplyAuthwise> getClaimApplyListForPendingForManager(int empId);
   public List<GetClaimApplyAuthwise> getClaimApplyListForPendingForAdmin();
   public List<GetClaimApplyAuthwise> getClaimApplyList2(int empId);
   public GetClaimApplyAuthwise getClaimApplyDetails(int claimId);
}