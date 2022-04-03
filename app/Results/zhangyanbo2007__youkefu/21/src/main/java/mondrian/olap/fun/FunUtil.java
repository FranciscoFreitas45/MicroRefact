package mondrian.olap.fun;
 import mondrian.calc;
import mondrian.calc.impl;
import mondrian.mdx;
import mondrian.olap;
import mondrian.olap.type;
import mondrian.resource.MondrianResource;
import mondrian.rolap;
import mondrian.util;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.log4j.Logger;
import java.util;
public class FunUtil extends Util{

 private  Logger LOGGER;

 private  String SORT_TIMING_NAME;

 private  String SORT_EVAL_TIMING_NAME;

 static  String[] emptyStringArray;

 private  boolean debug;

 public  NullMember NullMember;

 public  double DoubleNull;

 public  double DoubleEmpty;

 public  int IntegerNull;

 public  boolean BooleanNull;

 public  int TOO_SMALL;

 private  Logger LOGGER;

 private  T[] vec;

 private  Comparator<T> comp;

 private  boolean traced;

 private  long partitions;

 private  Logger LOGGER;

 final  Evaluator evaluator;

 final  Calc exp;

 private  int descMask;

 private  Map<Member,Object> valueMap;

 final  int arity;

 private Evaluator evaluator;

 final  Calc calc;

 private  boolean desc;

 private  Map<List<Member>,Object> valueMap;

 private  boolean post;

 private  boolean post;

 private List v;

 public  int errorCount;

 static  DescendingValueComparator instance;

 final  boolean descending;

 final  boolean brk;

 private  Calc key;

 private  Flag direction;

 private  Member member;

 final  T t;

 final  int i;


public void select(int limit){
    int n = vec.length - 1;
    select(limit, 0, n);
    if (traced) {
        traceStats("quickselect for " + limit + " from" + n + "items");
    }
}


public boolean isCalculated(){
    throw new UnsupportedOperationException();
}


public boolean worthCaching(Exp exp){
    // Literal is not worth caching.
    if (exp instanceof Literal) {
        return false;
    }
    // Member, hierarchy, level, or dimension expression is not worth
    // caching.
    if (exp instanceof MemberExpr || exp instanceof LevelExpr || exp instanceof HierarchyExpr || exp instanceof DimensionExpr) {
        return false;
    }
    if (exp instanceof ResolvedFunCall) {
        ResolvedFunCall call = (ResolvedFunCall) exp;
        // A set of literals is not worth caching.
        if (call.getFunDef() instanceof SetFunDef) {
            for (Exp setArg : call.getArgs()) {
                if (worthCaching(setArg)) {
                    return true;
                }
            }
            return false;
        }
    }
    return true;
}


public Object _covariance(SetWrapper sw1,SetWrapper sw2,boolean biased){
    if (sw1.v.size() != sw2.v.size()) {
        return Util.nullValue;
    }
    double avg1 = _avg(sw1);
    double avg2 = _avg(sw2);
    double covar = 0.0;
    for (int i = 0; i < sw1.v.size(); i++) {
        // all of this casting seems inefficient - can we make SetWrapper
        // contain an array of double instead?
        double diff1 = (((Number) sw1.v.get(i)).doubleValue() - avg1);
        double diff2 = (((Number) sw2.v.get(i)).doubleValue() - avg2);
        covar += (diff1 * diff2);
    }
    int n = sw1.v.size();
    if (!biased) {
        n--;
    }
    return new Double(covar / (double) n);
}


public boolean isParentChildLeaf(){
    return false;
}


public String getCaption(){
    throw new UnsupportedOperationException();
}


@Override
public void memberComplete(){
    members[0] = resolveMember(hierarchyList.get(0));
    segmentList.clear();
}


public String getPropertyFormattedValue(String propertyName){
    throw new UnsupportedOperationException();
}


public Member getParentMember(){
    throw new UnsupportedOperationException();
}


public int decodeReturnCategory(String flags){
    final int returnCategory = decodeCategory(flags, 1);
    if ((returnCategory & Category.Mask) != returnCategory) {
        throw newInternal("bad return code flag in flags '" + flags + "'");
    }
    return returnCategory;
}


public Map<Member,Object> evaluateMembers(Evaluator evaluator,Calc exp,Iterable<Member> memberIter,List<Member> memberList,boolean parentsToo){
    final int savepoint = evaluator.savepoint();
    try {
        assert exp.getType() instanceof ScalarType;
        Map<Member, Object> mapMemberToValue = new HashMap<Member, Object>();
        for (Member member : memberIter) {
            if (memberList != null) {
                memberList.add(member);
            }
            while (true) {
                evaluator.setContext(member);
                Object result = exp.evaluate(evaluator);
                if (result == null) {
                    result = Util.nullValue;
                }
                mapMemberToValue.put(member, result);
                if (!parentsToo) {
                    break;
                }
                member = member.getParentMember();
                if (member == null) {
                    break;
                }
                if (mapMemberToValue.containsKey(member)) {
                    break;
                }
            }
        }
        return mapMemberToValue;
    } finally {
        evaluator.restore(savepoint);
    }
}


public E getLiteralArg(ResolvedFunCall call,int i,E defaultValue,Class<E> allowedValues){
    if (i >= call.getArgCount()) {
        if (defaultValue == null) {
            throw newEvalException(call.getFunDef(), "Required argument is missing");
        } else {
            return defaultValue;
        }
    }
    Exp arg = call.getArg(i);
    if (!(arg instanceof Literal) || arg.getCategory() != Category.Symbol) {
        throw newEvalException(call.getFunDef(), "Expected a symbol, found '" + arg + "'");
    }
    String s = (String) ((Literal) arg).getValue();
    for (E e : allowedValues.getEnumConstants()) {
        if (e.name().equalsIgnoreCase(s)) {
            return e;
        }
    }
    StringBuilder buf = new StringBuilder(64);
    int k = 0;
    for (E e : allowedValues.getEnumConstants()) {
        if (k++ > 0) {
            buf.append(", ");
        }
        buf.append(e.name());
    }
    throw newEvalException(call.getFunDef(), "Allowed values are: {" + buf + "}");
}


public double quartile(Evaluator evaluator,TupleList members,Calc exp,int range){
    assert range >= 1 && range <= 3;
    SetWrapper sw = evaluateSet(evaluator, members, exp);
    if (sw.errorCount > 0) {
        return Double.NaN;
    } else if (sw.v.size() == 0) {
        return DoubleNull;
    }
    double[] asArray = new double[sw.v.size()];
    for (int i = 0; i < asArray.length; i++) {
        asArray[i] = ((Double) sw.v.get(i)).doubleValue();
    }
    Arrays.sort(asArray);
    // get a quartile, median is a second q
    double dm = 0.25 * asArray.length * range;
    int median = (int) Math.floor(dm);
    return dm == median && median < asArray.length - 1 ? (asArray[median] + asArray[median + 1]) / 2 : asArray[median];
}


public Object var(Evaluator evaluator,TupleList members,Calc exp,boolean biased){
    SetWrapper sw = evaluateSet(evaluator, members, exp);
    return _var(sw, biased);
}


public Member ancestor(Evaluator evaluator,Member member,int distance,Level targetLevel){
    if ((targetLevel != null) && (member.getHierarchy() != targetLevel.getHierarchy())) {
        throw MondrianResource.instance().MemberNotInLevelHierarchy.ex(member.getUniqueName(), targetLevel.getUniqueName());
    }
    if (distance == 0) {
        // Shortcut if there's nowhere to go.
        return member;
    } else if (distance < 0) {
        // Can't go backwards.
        return member.getHierarchy().getNullMember();
    }
    final List<Member> ancestors = new ArrayList<Member>();
    final SchemaReader schemaReader = evaluator.getSchemaReader();
    schemaReader.getMemberAncestors(member, ancestors);
    Member result = member.getHierarchy().getNullMember();
    searchLoop: for (int i = 0; i < ancestors.size(); i++) {
        final Member ancestorMember = ancestors.get(i);
        if (targetLevel != null) {
            if (ancestorMember.getLevel() == targetLevel) {
                if (schemaReader.isVisible(ancestorMember)) {
                    result = ancestorMember;
                    break;
                } else {
                    result = member.getHierarchy().getNullMember();
                    break;
                }
            }
        } else {
            if (schemaReader.isVisible(ancestorMember)) {
                distance--;
                // Make sure that this ancestor is really on the right
                // targetLevel. If a targetLevel was specified and at least
                // one of the ancestors was hidden, this this algorithm goes
                // too far up the ancestor list. It's not a problem, except
                // that we need to check if it's happened and return the
                // hierarchy's null member instead.
                // 
                // For example, consider what happens with
                // Ancestor([Store].[Israel].[Haifa], [Store].[Store
                // State]).  The distance from [Haifa] to [Store State] is
                // 1, but that lands us at the country targetLevel, which is
                // clearly wrong.
                if (distance == 0) {
                    result = ancestorMember;
                    break;
                }
            }
        }
    }
    return result;
}


public Member[] parseTuple(Evaluator evaluator,String string,List<Hierarchy> hierarchies){
    final Member[] members = new Member[hierarchies.size()];
    int i = parseTuple(evaluator, string, 0, members, hierarchies);
    // todo: check for garbage at end of string
    if (FunUtil.tupleContainsNullMember(members)) {
        return null;
    }
    return members;
}


public Property[] getProperties(){
    throw new UnsupportedOperationException();
}


public void checkIterListResultStyles(Calc calc){
    switch(calc.getResultStyle()) {
        case ITERABLE:
        case LIST:
        case MUTABLE_LIST:
            break;
        default:
            throw ResultStyleException.generateBadType(ResultStyle.ITERABLE_LIST_MUTABLELIST, calc.getResultStyle());
    }
}


public List<T> stablePartialSort(List<T> list,Comparator<T> comp,int limit,int algorithm){
    assert limit <= list.size();
    assert list.size() > 0;
    for (; ; ) {
        switch(algorithm) {
            case 0:
                float ratio = (float) limit / (float) list.size();
                if (ratio <= .05) {
                    // julian's algorithm
                    algorithm = 4;
                } else if (ratio <= .35) {
                    // marc's algorithm
                    algorithm = 2;
                } else {
                    // array sort
                    algorithm = 1;
                }
                break;
            case 1:
                return stablePartialSortArray(list, comp, limit);
            case 2:
                return stablePartialSortMarc(list, comp, limit);
            case 3:
                return stablePartialSortPedro(list, comp, limit);
            case 4:
                return stablePartialSortJulian(list, comp, limit);
            default:
                throw new RuntimeException();
        }
    }
}


public List<Member> parseMemberList(Evaluator evaluator,String string,Hierarchy hierarchy){
    IdentifierParser.MemberListBuilder builder = new IdentifierParser.MemberListBuilder(evaluator.getSchemaReader(), evaluator.getCube(), hierarchy);
    IdentifierParser.parseMemberList(builder, string);
    return builder.memberList;
}


public void toPercent(TupleList members,Map<List<Member>,Object> mapMemberToValue){
    double total = 0;
    int memberCount = members.size();
    for (int i = 0; i < memberCount; i++) {
        final List<Member> key = members.get(i);
        final Object o = mapMemberToValue.get(key);
        if (o instanceof Number) {
            total += ((Number) o).doubleValue();
        }
    }
    for (int i = 0; i < memberCount; i++) {
        final List<Member> key = members.get(i);
        final Object o = mapMemberToValue.get(key);
        if (o instanceof Number) {
            double d = ((Number) o).doubleValue();
            mapMemberToValue.put(key, d / total * (double) 100);
        }
    }
}


public double percentile(Evaluator evaluator,TupleList members,Calc exp,double p){
    SetWrapper sw = evaluateSet(evaluator, members, exp);
    if (sw.errorCount > 0) {
        return Double.NaN;
    } else if (sw.v.size() == 0) {
        return FunUtil.DoubleNull;
    }
    double[] asArray = new double[sw.v.size()];
    for (int i = 0; i < asArray.length; i++) {
        asArray[i] = (Double) sw.v.get(i);
    }
    Arrays.sort(asArray);
    // The median is defined as the value that has exactly the same
    // number of entries before it in the sorted list as after.
    // So, if the number of entries in the list is odd, the
    // median is the entry at (length-1)/2 (using zero-based indexes).
    // If the number of entries is even, the median is defined as the
    // arithmetic mean of the two numbers in the middle of the list, or
    // (entries[length/2 - 1] + entries[length/2]) / 2.
    int length = asArray.length;
    if (p <= 0.0) {
        return asArray[0];
    } else if (p >= 1.0) {
        return asArray[length - 1];
    } else if (length == 1) {
        return asArray[0];
    } else if (p == 0.5) {
        // Special case for median.
        if ((length & 1) == 1) {
            // The length is odd. Note that length/2 is an integer
            // expression, and it's positive so we save ourselves a divide.
            return asArray[length >> 1];
        } else {
            return (asArray[(length >> 1) - 1] + asArray[length >> 1]) / 2.0;
        }
    } else {
        final double jD = Math.floor(length * p);
        int j = jD > 0 ? (int) jD - 1 : (int) jD;
        double alpha = (p * length) - jD;
        assert alpha >= 0;
        assert alpha <= 1;
        return asArray[j] + ((asArray[j + 1] - asArray[j]) * alpha);
    }
}


public double correlation(Evaluator evaluator,TupleList memberList,Calc exp1,Calc exp2){
    SetWrapper sw1 = evaluateSet(evaluator, memberList, exp1);
    SetWrapper sw2 = evaluateSet(evaluator, memberList, exp2);
    Object covar = _covariance(sw1, sw2, false);
    // this should be false, yes?
    Object var1 = _var(sw1, false);
    Object var2 = _var(sw2, false);
    return ((Number) covar).doubleValue() / Math.sqrt(((Number) var1).doubleValue() * ((Number) var2).doubleValue());
}


@Override
public int size(){
    return length;
}


public void appendTuple(StringBuilder buf,Member[] members){
    buf.append("(");
    for (int j = 0; j < members.length; j++) {
        if (j > 0) {
            buf.append(", ");
        }
        Member member = members[j];
        buf.append(member.getUniqueName());
    }
    buf.append(")");
}


public void setProperty(String name,Object value){
    throw new UnsupportedOperationException();
}


public List<Member> periodsToDate(Evaluator evaluator,Level level,Member member){
    if (member == null) {
        member = evaluator.getContext(level.getHierarchy());
    }
    Member m = member;
    while (m != null) {
        if (m.getLevel() == level) {
            break;
        }
        m = m.getParentMember();
    }
    // If m == null, then "level" was lower than member's level.
    // periodsToDate([Time].[Quarter], [Time].[1997] is valid,
    // but will return an empty List
    List<Member> members = new ArrayList<Member>();
    if (m != null) {
        // e.g. m is [Time].[1997] and member is [Time].[1997].[Q1].[3]
        // we now have to make m to be the first member of the range,
        // so m becomes [Time].[1997].[Q1].[1]
        SchemaReader reader = evaluator.getSchemaReader();
        m = Util.getFirstDescendantOnLevel(reader, m, member.getLevel());
        reader.getMemberRange(level, m, member, members);
    }
    return members;
}


public TupleList levelMembers(Level level,Evaluator evaluator,boolean includeCalcMembers){
    List<Member> memberList = getNonEmptyLevelMembers(evaluator, level, includeCalcMembers);
    TupleList tupleList;
    if (!includeCalcMembers) {
        memberList = removeCalculatedMembers(memberList);
    }
    final List<Member> memberListClone = new ArrayList<Member>(memberList);
    tupleList = new UnaryTupleList(memberListClone);
    return hierarchizeTupleList(tupleList, false);
}


public double _avg(SetWrapper sw){
    double sum = 0.0;
    for (int i = 0; i < sw.v.size(); i++) {
        sum += ((Number) sw.v.get(i)).doubleValue();
    }
    // TODO: should look at context and optionally include nulls
    return sum / (double) sw.v.size();
}


public int compareMemberOrderKeysHierarchically(OrderKey k1,OrderKey k2){
    // null is less than anything else
    if (k1 == Util.nullValue) {
        return -1;
    }
    if (k2 == Util.nullValue) {
        return 1;
    }
    Member m1 = k1.member;
    Member m2 = k2.member;
    if (FunUtil.equals(m1, m2)) {
        return 0;
    }
    while (true) {
        int depth1 = m1.getDepth(), depth2 = m2.getDepth();
        if (depth1 < depth2) {
            m2 = m2.getParentMember();
            if (FunUtil.equals(m1, m2)) {
                return -1;
            }
        } else if (depth1 > depth2) {
            m1 = m1.getParentMember();
            if (FunUtil.equals(m1, m2)) {
                return 1;
            }
        } else {
            Member prev1 = m1, prev2 = m2;
            m1 = m1.getParentMember();
            m2 = m2.getParentMember();
            if (FunUtil.equals(m1, m2)) {
                OrderKey pk1 = new OrderKey(prev1);
                OrderKey pk2 = new OrderKey(prev2);
                return FunUtil.compareValues(pk1, pk2);
            }
        }
    }
}


public void setName(String name){
    throw new UnsupportedOperationException();
}


public int compare(Object o1,Object o2){
    return -compareValues(o1, o2);
}


public int getDepth(){
    throw new UnsupportedOperationException();
}


public Object _var(SetWrapper sw,boolean biased){
    if (sw.errorCount > 0) {
        return new Double(Double.NaN);
    } else if (sw.v.size() == 0) {
        return Util.nullValue;
    } else {
        double stdev = 0.0;
        double avg = _avg(sw);
        for (int i = 0; i < sw.v.size(); i++) {
            stdev += Math.pow((((Number) sw.v.get(i)).doubleValue() - avg), 2);
        }
        int n = sw.v.size();
        if (!biased) {
            n--;
        }
        return new Double(stdev / (double) n);
    }
}


public List<Member> sortMembers(Evaluator evaluator,Iterable<Member> memberIter,List<Member> memberList,List<SortKeySpec> keySpecList){
    if ((memberList != null) && (memberList.size() <= 1)) {
        return memberList;
    }
    if (memberList == null) {
        memberList = new ArrayList<Member>();
        for (Member member : memberIter) {
            memberList.add(member);
        }
        if (memberList.size() <= 1) {
            return memberList;
        }
    }
    ComparatorChain chain = new ComparatorChain();
    for (SortKeySpec key : keySpecList) {
        boolean brk = key.direction.brk;
        MemberComparator comp;
        if (brk) {
            comp = new BreakMemberComparator(evaluator, key.key, key.direction.descending);
        } else {
            comp = new HierarchicalMemberComparator(evaluator, key.key, key.direction.descending);
        }
        comp.preloadValues(memberList);
        chain.addComparator(comp.wrap(), false);
    }
    Collections.sort(memberList, chain);
    return memberList;
}


public Object covariance(Evaluator evaluator,TupleList members,Calc exp1,Calc exp2,boolean biased){
    final int savepoint = evaluator.savepoint();
    SetWrapper sw1;
    try {
        sw1 = evaluateSet(evaluator, members, exp1);
    } finally {
        evaluator.restore(savepoint);
    }
    SetWrapper sw2;
    try {
        sw2 = evaluateSet(evaluator, members, exp2);
    } finally {
        evaluator.restore(savepoint);
    }
    // todo: because evaluateSet does not add nulls to the SetWrapper, this
    // solution may lead to mismatched lists and is therefore not robust
    return _covariance(sw1, sw2, biased);
}


public Object sum(Evaluator evaluator,TupleList members,Calc exp){
    double d = sumDouble(evaluator, members, exp);
    return d == DoubleNull ? Util.nullValue : new Double(d);
}


public String[] getNames(){
    List<String> names = new ArrayList<String>();
    for (Flag flags : Flag.class.getEnumConstants()) {
        names.add(flags.name());
    }
    return names.toArray(new String[names.size()]);
}


public int compareHierarchically(Member m1,Member m2,boolean post){
    // Strip away the LimitedRollupMember wrapper, if it exists. The
    // wrapper does not implement equals and comparisons correctly. This
    // is safe this method has no side-effects: it just returns an int.
    m1 = unwrapLimitedRollupMember(m1);
    m2 = unwrapLimitedRollupMember(m2);
    if (equals(m1, m2)) {
        return 0;
    }
    while (true) {
        int depth1 = m1.getDepth();
        int depth2 = m2.getDepth();
        if (depth1 < depth2) {
            m2 = m2.getParentMember();
            if (equals(m1, m2)) {
                return post ? 1 : -1;
            }
        } else if (depth1 > depth2) {
            m1 = m1.getParentMember();
            if (equals(m1, m2)) {
                return post ? -1 : 1;
            }
        } else {
            Member prev1 = m1;
            Member prev2 = m2;
            m1 = unwrapLimitedRollupMember(m1.getParentMember());
            m2 = unwrapLimitedRollupMember(m2.getParentMember());
            if (equals(m1, m2)) {
                final int c = compareSiblingMembers(prev1, prev2);
                // compareHierarchically needs to impose a total order;
                // cannot return 0 for non-equal members
                assert c != 0 : "Members " + prev1 + ", " + prev2 + " are not equal, but compare returned 0.";
                return c;
            }
        }
    }
}


public Syntax decodeSyntacticType(String flags){
    char c = flags.charAt(0);
    switch(c) {
        case 'p':
            return Syntax.Property;
        case 'f':
            return Syntax.Function;
        case 'm':
            return Syntax.Method;
        case 'i':
            return Syntax.Infix;
        case 'P':
            return Syntax.Prefix;
        case 'Q':
            return Syntax.Postfix;
        case 'I':
            return Syntax.Internal;
        default:
            throw newInternal("unknown syntax code '" + c + "' in string '" + flags + "'");
    }
}


public Member parseMember(Evaluator evaluator,String string,Hierarchy hierarchy){
    Member[] members = { null };
    int i = parseMember(evaluator, string, 0, members, hierarchy);
    // todo: check for garbage at end of string
    final Member member = members[0];
    if (member == null) {
        throw MondrianResource.instance().MdxChildObjectNotFound.ex(string, evaluator.getCube().getQualifiedName());
    }
    return member;
}


public Object min(Evaluator evaluator,TupleList members,Calc calc){
    SetWrapper sw = evaluateSet(evaluator, members, calc);
    if (sw.errorCount > 0) {
        return Double.NaN;
    } else {
        final int size = sw.v.size();
        if (size == 0) {
            return Util.nullValue;
        } else {
            Double min = ((Number) sw.v.get(0)).doubleValue();
            for (int i = 1; i < size; i++) {
                Double iValue = ((Number) sw.v.get(i)).doubleValue();
                if (iValue < min) {
                    min = iValue;
                }
            }
            return min;
        }
    }
}


public int partition(int start,int end){
    partitions++;
    assert start <= end;
    // Find median of three (both ends and the middle).
    // TODO: use pseudo-median of nine when array segment is big enough.
    int mid = (start + end) / 2;
    order3(start, mid, end);
    if (end - start <= 2) {
        // sorted!
        return mid;
    }
    // Now the left and right ends are in place (ie in the correct
    // partition), and will serve as sentinels for scanning. Pick middle
    // as pivot and set it aside, in penultimate position.
    final T pivot = vec[mid];
    swap(mid, end - 1);
    // Scan inward from both ends, swapping misplaced items.
    // vec[start] is in place
    int left = start + 1;
    // vec[end - 1] is pivot
    int right = end - 2;
    while (left < right) {
        // scan past items in correct place, but stop at a pivot value
        // (Sedgewick's idea).
        while (less(vec[left], pivot)) {
            ++left;
        }
        while (less(pivot, vec[right])) {
            --right;
        }
        if (debug) {
            assert (left <= end) && (right >= start);
        }
        if (left < right) {
            // found a misplaced pair
            swap(left, right);
            ++left;
            --right;
        }
    }
    if ((left == right) && less(vec[left], pivot)) {
        ++left;
    }
    // All scanned. Restore pivot to its rightful place.
    swap(left, end - 1);
    if (debug) {
        for (int i = start; i < left; i++) {
            assert !more(vec[i], pivot);
        }
        assert equal(vec[left], pivot);
        for (int i = left + 1; i <= end; i++) {
            assert !less(vec[i], pivot);
        }
    }
    return left;
}


public List<Member> getNonEmptyMemberChildren(Evaluator evaluator,Member member){
    SchemaReader sr = evaluator.getSchemaReader();
    if (evaluator.isNonEmpty()) {
        return sr.getMemberChildren(member, evaluator);
    } else {
        return sr.getMemberChildren(member);
    }
}


@Override
public T get(int index){
    return pairs[index].t;
}


public void preloadValues(TupleList tuples){
    for (List<Member> t : tuples) {
        compute(t);
    }
}


public SetWrapper[] evaluateSet(Evaluator evaluator,TupleList list,DoubleCalc[] calcs){
    Util.assertPrecondition(calcs != null, "calcs != null");
    // todo: treat constant exps as evaluateMembers() does
    SetWrapper[] retvals = new SetWrapper[calcs.length];
    for (int i = 0; i < calcs.length; i++) {
        retvals[i] = new SetWrapper();
    }
    final TupleCursor cursor = list.tupleCursor();
    while (cursor.forward()) {
        cursor.setContext(evaluator);
        for (int i = 0; i < calcs.length; i++) {
            DoubleCalc calc = calcs[i];
            SetWrapper retval = retvals[i];
            double o = calc.evaluateDouble(evaluator);
            if (o == FunUtil.DoubleNull) {
                retval.nullCount++;
                retval.v.add(null);
            } else {
                retval.v.add(o);
            }
        // TODO: If the expression yielded an error, carry on
        // summing, so that if we are running in a
        // BatchingCellReader, we find out all the dependent cells
        // we need
        }
    }
    return retvals;
}


public void checkNativeCompatible(Validator validator,FunDef funDef,Exp[] args){
    // If the first argument to a function is either:
    // 1) the measures dimension or
    // 2) a measures member where the function returns another member or
    // a set,
    // then these are functions that dynamically return one or more
    // members of the measures dimension.  In that case, we cannot use
    // native cross joins because the functions need to be executed to
    // determine the resultant measures.
    // 
    // As a result, we disallow functions like AllMembers applied on the
    // Measures dimension as well as functions like the range operator,
    // siblings, and lag, when the argument is a measure member.
    // However, we do allow functions like isEmpty, rank, and topPercent.
    // 
    // Also, the Set and Parentheses functions are ok since they're
    // essentially just containers.
    Query query = validator.getQuery();
    if (!(funDef instanceof SetFunDef) && !(funDef instanceof ParenthesesFunDef) && query != null && query.nativeCrossJoinVirtualCube()) {
        int[] paramCategories = funDef.getParameterCategories();
        if (paramCategories.length > 0) {
            final int cat0 = paramCategories[0];
            final Exp arg0 = args[0];
            switch(cat0) {
                case Category.Dimension:
                case Category.Hierarchy:
                    if (arg0 instanceof DimensionExpr && ((DimensionExpr) arg0).getDimension().isMeasures() && !(funDef instanceof HierarchyCurrentMemberFunDef)) {
                        query.setVirtualCubeNonNativeCrossJoin();
                    }
                    break;
                case Category.Member:
                    if (arg0 instanceof MemberExpr && ((MemberExpr) arg0).getMember().isMeasure() && isMemberOrSet(funDef.getReturnCategory())) {
                        query.setVirtualCubeNonNativeCrossJoin();
                    }
                    break;
            }
        }
    }
}


public String getLocalized(LocalizedProperty prop,Locale locale){
    throw new UnsupportedOperationException();
}


public Object max(Evaluator evaluator,TupleList members,Calc exp){
    SetWrapper sw = evaluateSet(evaluator, members, exp);
    if (sw.errorCount > 0) {
        return Double.NaN;
    } else {
        final int size = sw.v.size();
        if (size == 0) {
            return Util.nullValue;
        } else {
            Double max = ((Number) sw.v.get(0)).doubleValue();
            for (int i = 1; i < size; i++) {
                Double iValue = ((Number) sw.v.get(i)).doubleValue();
                if (iValue > max) {
                    max = iValue;
                }
            }
            return max;
        }
    }
}


public void traceStats(String prefix){
    StringBuilder sb = new StringBuilder(prefix);
    sb.append(": ");
    sb.append(partitions).append(" partitions, ");
    sb.append(comparisons).append(" comparisons, ");
    sb.append(exchanges).append(" exchanges.");
    LOGGER.debug(sb.toString());
}


public boolean more(T x,T y){
    comparisons++;
    return comp.compare(x, y) > 0;
}


public Member unwrapLimitedRollupMember(Member m){
    if (m instanceof RolapHierarchy.LimitedRollupMember) {
        return ((RolapHierarchy.LimitedRollupMember) m).member;
    }
    return m;
}


public Map<String,Annotation> getAnnotationMap(){
    throw new UnsupportedOperationException();
}


public Level getLevel(){
    throw new UnsupportedOperationException();
}


public List<Member> addMembers(SchemaReader schemaReader,List<Member> members,Level level){
    List<Member> levelMembers = schemaReader.getLevelMembers(level, true);
    members.addAll(levelMembers);
    return members;
}


public int compareHierarchicallyButSiblingsByValue(Member m1,Member m2){
    if (FunUtil.equals(m1, m2)) {
        return 0;
    }
    while (true) {
        int depth1 = m1.getDepth(), depth2 = m2.getDepth();
        if (depth1 < depth2) {
            m2 = m2.getParentMember();
            if (FunUtil.equals(m1, m2)) {
                return -1;
            }
        } else if (depth1 > depth2) {
            m1 = m1.getParentMember();
            if (FunUtil.equals(m1, m2)) {
                return 1;
            }
        } else {
            Member prev1 = m1, prev2 = m2;
            m1 = m1.getParentMember();
            m2 = m2.getParentMember();
            if (FunUtil.equals(m1, m2)) {
                // including case where both parents are null
                int c = compareByValue(prev1, prev2);
                if (c == 0) {
                    c = FunUtil.compareSiblingMembers(prev1, prev2);
                }
                return desc ? -c : c;
            }
        }
    }
}


public boolean isNull(){
    return true;
}


public boolean equals(Object obj){
    return this == obj || obj instanceof ObjIntPair && this.i == ((ObjIntPair) obj).i && Util.equals(this.t, ((ObjIntPair) obj).t);
}


public Member cousin2(SchemaReader schemaReader,Member member1,Member member2){
    if (member1.getLevel() == member2.getLevel()) {
        return member2;
    }
    Member uncle = cousin2(schemaReader, member1.getParentMember(), member2);
    if (uncle == null) {
        return null;
    }
    int ordinal = Util.getMemberOrdinalInParent(schemaReader, member1);
    List<Member> cousins = schemaReader.getMemberChildren(uncle);
    if (cousins.size() <= ordinal) {
        return null;
    }
    return cousins.get(ordinal);
}


public void order3(int i,int j,int k){
    if (more(vec[i], vec[j])) {
        swap(i, j);
    }
    if (more(vec[i], vec[k])) {
        swap(i, k);
    }
    if (more(vec[j], vec[k])) {
        swap(j, k);
    }
}


public String toString(){
    return "<" + t + ", " + i + ">";
}


public void checkCompatible(Exp left,Exp right,FunDef funDef){
    final Type leftType = TypeUtil.stripSetType(left.getType());
    final Type rightType = TypeUtil.stripSetType(right.getType());
    if (!TypeUtil.isUnionCompatible(leftType, rightType)) {
        throw newEvalException(funDef, "Expressions must have the same hierarchy");
    }
}


public List<Member> memberRange(Evaluator evaluator,Member startMember,Member endMember){
    final Level level = startMember.getLevel();
    assertTrue(level == endMember.getLevel());
    List<Member> members = new ArrayList<Member>();
    evaluator.getSchemaReader().getMemberRange(level, startMember, endMember, members);
    if (members.isEmpty()) {
        // The result is empty, so maybe the members are reversed. This is
        // cheaper than comparing the members before we call getMemberRange.
        evaluator.getSchemaReader().getMemberRange(level, endMember, startMember, members);
    }
    return members;
}


public Comparator<Member> wrap(){
    final MemberComparator comparator = this;
    if (LOGGER.isDebugEnabled()) {
        return new Comparator<Member>() {

            public int compare(Member m1, Member m2) {
                final int c = comparator.compare(m1, m2);
                // here guaranteed that eval(m) finds a memorized value
                LOGGER.debug("compare " + m1.getUniqueName() + "(" + eval(m1) + "), " + m2.getUniqueName() + "(" + eval(m2) + ")" + " yields " + c);
                return c;
            }
        };
    } else {
        return this;
    }
}


public String getUniqueName(){
    throw new UnsupportedOperationException();
}


public boolean equalTuple(Member[] members0,Member[] members1){
    final int count = members0.length;
    if (count != members1.length) {
        return false;
    }
    outer: for (int i = 0; i < count; i++) {
        // First check the member at the corresponding ordinal. It is more
        // likely to be there.
        final Member member0 = members0[i];
        if (member0.equals(members1[i])) {
            continue;
        }
        // Look for this member in other positions.
        // We can assume that the members in members0 are distinct (because
        // they belong to different dimensions), so this test is valid.
        for (int j = 0; j < count; j++) {
            if (i != j && member0.equals(members1[j])) {
                continue outer;
            }
        }
        // This member of members0 does not occur in any position of
        // members1. The tuples are not equal.
        return false;
    }
    return true;
}


public Member[] makeNullTuple(TupleType tupleType){
    final Type[] elementTypes = tupleType.elementTypes;
    Member[] members = new Member[elementTypes.length];
    for (int i = 0; i < elementTypes.length; i++) {
        MemberType type = (MemberType) elementTypes[i];
        members[i] = makeNullMember(type);
    }
    return members;
}


public String getName(){
    throw new UnsupportedOperationException();
}


public Hierarchy getDimensionDefaultHierarchy(Dimension dimension){
    final Hierarchy[] hierarchies = dimension.getHierarchies();
    if (hierarchies.length == 1) {
        return hierarchies[0];
    }
    if (MondrianProperties.instance().SsasCompatibleNaming.get()) {
        // In SSAS 2005, dimensions with more than one hierarchy do not have
        // a default hierarchy.
        return null;
    }
    for (Hierarchy hierarchy : hierarchies) {
        if (hierarchy.getName() == null || hierarchy.getUniqueName().equals(dimension.getUniqueName())) {
            return hierarchy;
        }
    }
    return null;
}


public TupleList parseTupleList(Evaluator evaluator,String string,List<Hierarchy> hierarchies){
    final IdentifierParser.TupleListBuilder builder = new IdentifierParser.TupleListBuilder(evaluator.getSchemaReader(), evaluator.getCube(), hierarchies);
    IdentifierParser.parseTupleList(builder, string);
    return builder.tupleList;
}


public boolean isMeasure(){
    throw new UnsupportedOperationException();
}


public int compareTo(Object o){
    assert o instanceof OrderKey;
    Member otherMember = ((OrderKey) o).member;
    final boolean thisCalculated = this.member.isCalculatedInQuery();
    final boolean otherCalculated = otherMember.isCalculatedInQuery();
    if (thisCalculated) {
        if (!otherCalculated) {
            return 1;
        }
    } else {
        if (otherCalculated) {
            return -1;
        }
    }
    final Comparable thisKey = this.member.getOrderKey();
    final Comparable otherKey = otherMember.getOrderKey();
    if ((thisKey != null) && (otherKey != null)) {
        return thisKey.compareTo(otherKey);
    } else {
        return this.member.compareTo(otherMember);
    }
}


public void checkListResultStyles(Calc calc){
    switch(calc.getResultStyle()) {
        case LIST:
        case MUTABLE_LIST:
            break;
        default:
            throw ResultStyleException.generateBadType(ResultStyle.LIST_MUTABLELIST, calc.getResultStyle());
    }
}


public TupleList hierarchyMembers(Hierarchy hierarchy,Evaluator evaluator,boolean includeCalcMembers){
    TupleList tupleList = new UnaryTupleList();
    final List<Member> memberList = tupleList.slice(0);
    if (evaluator.isNonEmpty()) {
        // Allow the SQL generator to generate optimized SQL since we know
        // we're only interested in non-empty members of this level.
        for (Level level : hierarchy.getLevels()) {
            List<Member> members = getNonEmptyLevelMembers(evaluator, level, includeCalcMembers);
            memberList.addAll(members);
        }
    } else {
        final List<Member> memberList1 = addMembers(evaluator.getSchemaReader(), new ConcatenableList<Member>(), hierarchy);
        if (includeCalcMembers) {
            memberList.addAll(memberList1);
        } else {
            // Same effect as calling removeCalculatedMembers(tupleList)
            // but one fewer copy of the list.
            for (Member member1 : memberList1) {
                if (!member1.isCalculated()) {
                    memberList.add(member1);
                }
            }
        }
    }
    return hierarchizeTupleList(tupleList, false);
}


public Object compute(List<Member> key){
    evaluator.setContext(key);
    Object val = calc.evaluate(evaluator);
    if (val == null) {
        val = Util.nullValue;
    }
    valueMap.put(key, val);
    return val;
}


public Member getDataMember(){
    throw new UnsupportedOperationException();
}


public OlapElement lookupChild(SchemaReader schemaReader,Id.Segment s,MatchType matchType){
    throw new UnsupportedOperationException();
}


public List<T> stablePartialSortJulian(List<T> list,Comparator<T> comp,int limit){
    final Comparator<ObjIntPair<T>> comp2 = new Comparator<ObjIntPair<T>>() {

        public int compare(ObjIntPair<T> o1, ObjIntPair<T> o2) {
            int c = comp.compare(o1.t, o2.t);
            if (c == 0) {
                c = Util.compare(o1.i, o2.i);
            }
            return -c;
        }
    };
    int filled = 0;
    final PriorityQueue<ObjIntPair<T>> queue = new PriorityQueue<ObjIntPair<T>>(limit, comp2);
    for (T element : list) {
        if (filled < limit) {
            queue.offer(new ObjIntPair<T>(element, filled++));
        } else {
            ObjIntPair<T> head = queue.element();
            if (comp.compare(element, head.t) <= 0) {
                ObjIntPair<T> item = new ObjIntPair<T>(element, filled++);
                if (comp2.compare(item, head) >= 0) {
                    ObjIntPair poll = queue.remove();
                    Util.discard(poll);
                    queue.offer(item);
                }
            }
        }
    }
    int n = queue.size();
    final Object[] elements = new Object[n];
    while (n > 0) {
        elements[--n] = queue.poll().t;
    }
    assert queue.isEmpty();
    // noinspection unchecked
    return Arrays.asList((T[]) elements);
}


public Dimension getDimension(){
    throw new UnsupportedOperationException();
}


public void tupleComplete(){
    super.tupleComplete();
    memberList.toArray(members);
}


public TupleList removeCalculatedMembers(TupleList memberList){
    if (memberList.getArity() == 1) {
        return new UnaryTupleList(removeCalculatedMembers(memberList.slice(0)));
    } else {
        final TupleList clone = memberList.cloneList(memberList.size());
        outer: for (List<Member> members : memberList) {
            for (Member member : members) {
                if (member.isCalculated()) {
                    continue outer;
                }
            }
            clone.add(members);
        }
        return clone;
    }
}


public int compareByValue(Member m1,Member m2){
    int c;
    final int savepoint = evaluator.savepoint();
    try {
        evaluator.setContext(m1);
        Object v1 = calc.evaluate(evaluator);
        evaluator.setContext(m2);
        Object v2 = calc.evaluate(evaluator);
        c = FunUtil.compareValues(v1, v2);
        return c;
    } finally {
        // important to restore the evaluator state
        evaluator.restore(savepoint);
    }
}


public Map<Member,Access> getNonEmptyMemberChildrenWithDetails(Evaluator evaluator,Member member){
    SchemaReader sr = evaluator.getSchemaReader();
    if (evaluator.isNonEmpty()) {
        return (Map<Member, Access>) sr.getMemberChildrenWithDetails(member, evaluator);
    } else {
        return (Map<Member, Access>) sr.getMemberChildrenWithDetails(member, null);
    }
}


public Object stdev(Evaluator evaluator,TupleList members,Calc exp,boolean biased){
    Object o = var(evaluator, members, exp, biased);
    return (o instanceof Double) ? new Double(Math.sqrt(((Number) o).doubleValue())) : o;
}


public Calc getKey(){
    return this.key;
}


public int compareSiblingMembers(Member m1,Member m2){
    // calculated members collate after non-calculated
    final boolean calculated1 = m1.isCalculatedInQuery();
    final boolean calculated2 = m2.isCalculatedInQuery();
    if (calculated1) {
        if (!calculated2) {
            return 1;
        }
    } else {
        if (calculated2) {
            return -1;
        }
    }
    final Comparable k1 = m1.getOrderKey();
    final Comparable k2 = m2.getOrderKey();
    if ((k1 != null) && (k2 != null)) {
        return k1.compareTo(k2);
    } else {
        final int ordinal1 = m1.getOrdinal();
        final int ordinal2 = m2.getOrdinal();
        return (ordinal1 == ordinal2) ? m1.compareTo(m2) : (ordinal1 < ordinal2) ? -1 : 1;
    }
}


public void swap(int i,int j){
    exchanges++;
    T temp = vec[i];
    vec[i] = vec[j];
    vec[j] = temp;
}


public int count(Evaluator evaluator,TupleIterable iterable,boolean includeEmpty){
    if (iterable == null) {
        return 0;
    }
    if (includeEmpty) {
        if (iterable instanceof TupleList) {
            return ((TupleList) iterable).size();
        } else {
            int retval = 0;
            TupleCursor cursor = iterable.tupleCursor();
            while (cursor.forward()) {
                retval++;
            }
            return retval;
        }
    } else {
        int retval = 0;
        TupleCursor cursor = iterable.tupleCursor();
        while (cursor.forward()) {
            cursor.setContext(evaluator);
            if (!evaluator.currentIsEmpty()) {
                retval++;
            }
        }
        return retval;
    }
}


public boolean less(T x,T y){
    comparisons++;
    return comp.compare(x, y) < 0;
}


public void sort(){
    int n = vec.length - 1;
    sort(0, n);
    if (traced) {
        traceStats("quicksort on " + n + "items");
    }
}


public boolean isVisible(){
    throw new UnsupportedOperationException();
}


public Map<List<Member>,Object> evaluateTuples(Evaluator evaluator,Calc exp,TupleList tuples){
    final int savepoint = evaluator.savepoint();
    try {
        assert exp.getType() instanceof ScalarType;
        final Map<List<Member>, Object> mapMemberToValue = new HashMap<List<Member>, Object>();
        for (int i = 0, count = tuples.size(); i < count; i++) {
            List<Member> tuple = tuples.get(i);
            evaluator.setContext(tuple);
            Object result = exp.evaluate(evaluator);
            if (result == null) {
                result = Util.nullValue;
            }
            mapMemberToValue.put(tuple, result);
        }
        return mapMemberToValue;
    } finally {
        evaluator.restore(savepoint);
    }
}


public List<List<Member>> partiallySortTuples(Evaluator evaluator,TupleList list,Calc exp,int limit,boolean desc){
    assert list.size() > 0;
    assert limit <= list.size();
    Comparator<List<Member>> comp = new BreakTupleComparator(evaluator, exp, list.getArity());
    if (desc) {
        comp = Collections.reverseOrder(comp);
    }
    return stablePartialSort(list, comp, limit);
}


public boolean isAncestorOf(Member m0,Member m1,boolean strict){
    if (strict) {
        if (m1 == null) {
            return false;
        }
        m1 = m1.getParentMember();
    }
    while (m1 != null) {
        if (m1.equals(m0)) {
            return true;
        }
        m1 = m1.getParentMember();
    }
    return false;
}


public int decodeCategory(String flags,int offset){
    char c = flags.charAt(offset);
    switch(c) {
        case 'a':
            return Category.Array;
        case 'd':
            return Category.Dimension;
        case 'h':
            return Category.Hierarchy;
        case 'l':
            return Category.Level;
        case 'b':
            return Category.Logical;
        case 'm':
            return Category.Member;
        case 'N':
            return Category.Numeric | Category.Constant;
        case 'n':
            return Category.Numeric;
        case 'I':
            return Category.Numeric | Category.Integer | Category.Constant;
        case 'i':
            return Category.Numeric | Category.Integer;
        case 'x':
            return Category.Set;
        case '#':
            return Category.String | Category.Constant;
        case 'S':
            return Category.String;
        case 't':
            return Category.Tuple;
        case 'v':
            return Category.Value;
        case 'y':
            return Category.Symbol;
        case 'U':
            return Category.Null;
        case 'e':
            return Category.Empty;
        case 'D':
            return Category.DateTime;
        default:
            throw newInternal("unknown type code '" + c + "' in string '" + flags + "'");
    }
}


public Exp getExpression(){
    throw new UnsupportedOperationException();
}


public Object eval(List<Member> t){
    Object val = valueMap.get(t);
    if (val != null) {
        return val;
    }
    return compute(t);
}


public List<Member> partiallySortMembers(Evaluator evaluator,List<Member> list,Calc exp,int limit,boolean desc){
    assert list.size() > 0;
    assert limit <= list.size();
    evaluator.getTiming().markStart(SORT_EVAL_TIMING_NAME);
    boolean timingEval = true;
    boolean timingSort = false;
    try {
        MemberComparator comp = new BreakMemberComparator(evaluator, exp, desc);
        Map<Member, Object> valueMap = evaluateMembers(evaluator, exp, list, null, false);
        evaluator.getTiming().markEnd(SORT_EVAL_TIMING_NAME);
        timingEval = false;
        evaluator.getTiming().markStart(SORT_TIMING_NAME);
        timingSort = true;
        comp.preloadValues(valueMap);
        return stablePartialSort(list, comp.wrap(), limit);
    } finally {
        if (timingEval) {
            evaluator.getTiming().markEnd(SORT_EVAL_TIMING_NAME);
        } else if (timingSort) {
            evaluator.getTiming().markEnd(SORT_TIMING_NAME);
        }
    }
}


public int getSolveOrder(){
    throw new UnsupportedOperationException();
}


public MemberType getMemberType(){
    throw new UnsupportedOperationException();
}


public RuntimeException newEvalException(String message,Throwable throwable){
    return new MondrianEvaluationException(message + ": " + Util.getErrorMessage(throwable));
}


public void selectionSort(int start,int end){
    for (int i = start; i < end; ++i) {
        // pick the min of vec[i, end]
        int pmin = i;
        for (int j = i + 1; j <= end; ++j) {
            if (less(vec[j], vec[pmin])) {
                pmin = j;
            }
        }
        if (pmin != i) {
            swap(i, pmin);
        }
    }
}


public List<Member> getAncestorMembers(){
    throw new UnsupportedOperationException();
}


public boolean isCalculatedInQuery(){
    throw new UnsupportedOperationException();
}


public FunDef createDummyFunDef(Resolver resolver,int returnCategory,Exp[] args){
    final int[] argCategories = ExpBase.getTypes(args);
    return new FunDefBase(resolver, returnCategory, argCategories) {
    };
}


public Flag getDirection(){
    return this.direction;
}


public List<Member> getNonEmptyLevelMembers(Evaluator evaluator,Level level,boolean includeCalcMembers){
    SchemaReader sr = evaluator.getSchemaReader();
    if (evaluator.isNonEmpty()) {
        List<Member> members = sr.getLevelMembers(level, evaluator);
        if (includeCalcMembers) {
            return addLevelCalculatedMembers(sr, level, members);
        }
        return members;
    }
    return sr.getLevelMembers(level, includeCalcMembers);
}


public Integer box(int n){
    return n == IntegerNull ? null : n;
}


public void addUnique(TupleList left,TupleList right,Set<List<Member>> set){
    assert left != null;
    assert right != null;
    if (right.isEmpty()) {
        return;
    }
    for (int i = 0, n = right.size(); i < n; i++) {
        List<Member> o = right.get(i);
        if (set.add(o)) {
            left.add(o);
        }
    }
}


public Comparable getOrderKey(){
    throw new UnsupportedOperationException();
}


public String getDescription(){
    throw new UnsupportedOperationException();
}


public void partialSort(int limit){
    int n = vec.length - 1;
    select(limit, 0, n);
    if (traced) {
        traceStats("partial sort: quickselect phase for " + limit + "from " + n + "items");
    }
    sort(0, limit - 1);
    if (traced) {
        traceStats("partial sort: quicksort phase on " + n + "items");
    }
}


public boolean isEvaluated(){
    throw new UnsupportedOperationException();
}


public boolean tupleContainsNullMember(List<Member> tuple){
    for (Member member : tuple) {
        if (member.isNull()) {
            return true;
        }
    }
    return false;
}


public String getQualifiedName(){
    throw new UnsupportedOperationException();
}


public Object avg(Evaluator evaluator,TupleList members,Calc calc){
    SetWrapper sw = evaluateSet(evaluator, members, calc);
    return (sw.errorCount > 0) ? new Double(Double.NaN) : (sw.v.size() == 0) ? Util.nullValue : new Double(_avg(sw));
}


public List<T> stablePartialSortPedro(List<T> list,Comparator<T> comp,int limit){
    final ObjIntPair<T>[] pairs = new ObjIntPair[limit];
    Comparator<ObjIntPair<T>> pairComp = new Comparator<ObjIntPair<T>>() {

        public int compare(ObjIntPair<T> x, ObjIntPair<T> y) {
            int val = comp.compare(x.t, y.t);
            if (val == 0) {
                val = x.i - y.i;
            }
            return val;
        }
    };
    int filled = 0;
    T maximum = null;
    int maximumIndex = 0;
    int originalIndex = 0;
    for (T item : list) {
        // O(n) to scan list
        switch(filled) {
            case 0:
                maximum = item;
                pairs[0] = new ObjIntPair<T>(item, originalIndex);
                filled++;
                break;
            default:
                if (filled < limit) {
                    pairs[filled] = new ObjIntPair<T>(item, originalIndex);
                    if (comp.compare(item, maximum) > 0) {
                        maximum = item;
                        maximumIndex = filled;
                    }
                    filled++;
                } else {
                    if (comp.compare(item, maximum) < 0) {
                        pairs[maximumIndex] = new ObjIntPair<T>(item, originalIndex);
                        maximum = pairs[0].t;
                        maximumIndex = 0;
                        for (int i = 0; i < filled; i++) {
                            if (comp.compare(pairs[i].t, maximum) > 0) {
                                maximum = pairs[i].t;
                                maximumIndex = i;
                            }
                        }
                    }
                }
        }
        originalIndex++;
    }
    Arrays.sort(pairs, pairComp);
    if (false)
        for (int i = 0; i < limit; i++) {
            T item = pairs[i].t;
            T originalItem = list.get(i);
            int itemIndex = pairs[i].i;
            if (itemIndex < i) {
                if (pairs[itemIndex].i > i) {
                    list.set(pairs[itemIndex].i, originalItem);
                }
            } else {
                list.set(itemIndex, originalItem);
            }
            list.set(i, item);
        }
    List<T> result = new ArrayList<T>(limit);
    for (int i = 0; i < limit; i++) {
        result.add(list.get(pairs[i].i));
    }
    return result;
}


public TupleList sortTuples(Evaluator evaluator,TupleIterable tupleIter,TupleList tupleList,List<SortKeySpec> keySpecList,int arity){
    if (tupleList == null) {
        tupleList = TupleCollections.createList(arity);
        TupleCursor cursor = tupleIter.tupleCursor();
        while (cursor.forward()) {
            tupleList.addCurrent(cursor);
        }
    }
    if (tupleList.size() <= 1) {
        return tupleList;
    }
    ComparatorChain chain = new ComparatorChain();
    for (SortKeySpec key : keySpecList) {
        boolean brk = key.direction.brk;
        boolean orderByKey = key.key.isWrapperFor(MemberOrderKeyFunDef.CalcImpl.class);
        if (brk) {
            TupleExpMemoComparator comp = new BreakTupleComparator(evaluator, key.key, arity);
            comp.preloadValues(tupleList);
            chain.addComparator(comp, key.direction.descending);
        } else if (orderByKey) {
            TupleExpMemoComparator comp = new HierarchicalTupleKeyComparator(evaluator, key.key, arity);
            comp.preloadValues(tupleList);
            chain.addComparator(comp, key.direction.descending);
        } else {
            TupleExpComparator comp = new HierarchicalTupleComparator(evaluator, key.key, arity, key.direction.descending);
            chain.addComparator(comp, false);
        }
    }
    Collections.sort(tupleList, chain);
    if (LOGGER.isDebugEnabled()) {
        StringBuilder sb = new StringBuilder("FunUtil.sortTuples returned:");
        for (List<Member> tuple : tupleList) {
            sb.append("\n");
            sb.append(tuple.toString());
        }
        LOGGER.debug(sb.toString());
    }
    return tupleList;
}


public int hashCode(){
    return Util.hash(i, t);
}


public int[] decodeParameterCategories(String flags){
    int[] parameterCategories = new int[flags.length() - 2];
    for (int i = 0; i < parameterCategories.length; i++) {
        parameterCategories[i] = decodeCategory(flags, i + 2);
    }
    return parameterCategories;
}


public List<T> stablePartialSortArray(List<T> list,Comparator<T> comp,int limit){
    ArrayList<T> list2 = new ArrayList<T>(list);
    Collections.sort(list2, comp);
    return list2.subList(0, limit);
}


public int compareValues(Object value0,Object value1){
    if (value0 == value1) {
        return 0;
    }
    // null is less than anything else
    if (value0 == null) {
        return -1;
    }
    if (value1 == null) {
        return 1;
    }
    if (value0 == RolapUtil.valueNotReadyException) {
        // the left value is not in cache; continue as best as we can
        return -1;
    } else if (value1 == RolapUtil.valueNotReadyException) {
        // the right value is not in cache; continue as best as we can
        return 1;
    } else if (value0 == Util.nullValue) {
        // null == -infinity
        return -1;
    } else if (value1 == Util.nullValue) {
        // null == -infinity
        return 1;
    } else if (value0 instanceof String) {
        return ((String) value0).compareToIgnoreCase((String) value1);
    } else if (value0 instanceof Number) {
        return FunUtil.compareValues(((Number) value0).doubleValue(), ((Number) value1).doubleValue());
    } else if (value0 instanceof Date) {
        return ((Date) value0).compareTo((Date) value1);
    } else if (value0 instanceof OrderKey) {
        return ((OrderKey) value0).compareTo(value1);
    } else {
        throw Util.newInternal("cannot compare " + value0);
    }
}


public int getOrdinal(){
    throw new UnsupportedOperationException();
}


public FunDef resolveFunArgs(Validator validator,FunDef funDef,Exp[] args,Exp[] newArgs,String name,Syntax syntax){
    for (int i = 0; i < args.length; i++) {
        newArgs[i] = validator.validate(args[i], false);
    }
    if (funDef == null || validator.alwaysResolveFunDef()) {
        funDef = validator.getDef(newArgs, name, syntax);
    }
    checkNativeCompatible(validator, funDef, newArgs);
    return funDef;
}


public int maybeNegate(int c){
    return descMask * c;
}


public String getParentUniqueName(){
    throw new UnsupportedOperationException();
}


public List<T> stablePartialSortMarc(List<T> list,Comparator<T> comp,int limit){
    assert limit >= 0;
    // Load an array of pairs {list-item, list-index}.
    // List-index is a secondary sort key, to give a stable sort.
    // REVIEW Can we use a simple T[], with the index implied?
    // REVIEW When limit is big relative to list size, faster to
    // mergesort. Test for this.
    // O(n) to scan list
    int n = list.size();
    @SuppressWarnings({ "unchecked" })
    final ObjIntPair<T>[] pairs = new ObjIntPair[n];
    int i = 0;
    for (T item : list) {
        // O(n) to scan list
        pairs[i] = new ObjIntPair<T>(item, i);
        ++i;
    }
    Comparator<ObjIntPair<T>> pairComp = new Comparator<ObjIntPair<T>>() {

        public int compare(ObjIntPair<T> x, ObjIntPair<T> y) {
            int val = comp.compare(x.t, y.t);
            if (val == 0) {
                val = x.i - y.i;
            }
            return val;
        }
    };
    final int length = Math.min(limit, n);
    // O(n + limit * log(limit)) to quicksort
    partialSort(pairs, pairComp, length);
    // Use an abstract list to avoid doing a copy. The result is immutable.
    return new AbstractList<T>() {

        @Override
        public T get(int index) {
            return pairs[index].t;
        }

        @Override
        public int size() {
            return length;
        }
    };
}


public boolean isMemberOrSet(int category){
    return category == Category.Member || category == Category.Set;
}


public boolean isAll(){
    return false;
}


public Member cousin(SchemaReader schemaReader,Member member,Member ancestorMember){
    if (ancestorMember.isNull()) {
        return ancestorMember;
    }
    if (member.getHierarchy() != ancestorMember.getHierarchy()) {
        throw MondrianResource.instance().CousinHierarchyMismatch.ex(member.getUniqueName(), ancestorMember.getUniqueName());
    }
    if (member.getLevel().getDepth() < ancestorMember.getLevel().getDepth()) {
        return member.getHierarchy().getNullMember();
    }
    Member cousin = cousin2(schemaReader, member, ancestorMember);
    if (cousin == null) {
        cousin = member.getHierarchy().getNullMember();
    }
    return cousin;
}


public boolean isChildOrEqualTo(Member member){
    throw new UnsupportedOperationException();
}


public TupleList hierarchizeTupleList(TupleList tupleList,boolean post){
    if (tupleList.isEmpty()) {
        TupleCollections.emptyList(tupleList.getArity());
    }
    final TupleList fixedList = tupleList.fix();
    if (tupleList.getArity() == 1) {
        hierarchizeMemberList(fixedList.slice(0), post);
        return fixedList;
    }
    Comparator<List<Member>> comparator = new HierarchizeTupleComparator(fixedList.getArity(), post);
    Collections.sort(fixedList, comparator);
    if (LOGGER.isDebugEnabled()) {
        StringBuilder sb = new StringBuilder("FunUtil.hierarchizeTupleList returned:");
        for (List<Member> tuple : fixedList) {
            sb.append("\n");
            sb.append(tuple.toString());
        }
    }
    return fixedList;
}


public boolean isHidden(){
    throw new UnsupportedOperationException();
}


public void hierarchizeMemberList(List<Member> memberList,boolean post){
    if (memberList.size() <= 1) {
        return;
    }
    if (memberList.get(0).getDimension().isHighCardinality()) {
        return;
    }
    Comparator<Member> comparator = new HierarchizeComparator(post);
    Collections.sort(memberList, comparator);
}


public boolean equal(T x,T y){
    comparisons++;
    return comp.compare(x, y) == 0;
}


public Member makeNullMember(MemberType memberType){
    Hierarchy hierarchy = memberType.getHierarchy();
    if (hierarchy == null) {
        return NullMember;
    }
    return hierarchy.getNullMember();
}


public Object getPropertyValue(String propertyName,boolean matchCase){
    throw new UnsupportedOperationException();
}


public double sumDouble(Evaluator evaluator,TupleIterable iterable,Calc exp){
    SetWrapper sw = evaluateSet(evaluator, iterable, exp);
    if (sw.errorCount > 0) {
        return Double.NaN;
    } else if (sw.v.size() == 0) {
        return DoubleNull;
    } else {
        double sum = 0.0;
        for (int i = 0; i < sw.v.size(); i++) {
            sum += ((Number) sw.v.get(i)).doubleValue();
        }
        return sum;
    }
}


public Hierarchy getHierarchy(){
    throw new UnsupportedOperationException();
}


}