public interface BonusCalcRepo {

   public List<BonusCalc> findByDelStatusAndBonusId(int i,int bonusId);
}