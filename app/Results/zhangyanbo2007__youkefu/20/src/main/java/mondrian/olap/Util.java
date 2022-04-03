package mondrian.olap;
 import mondrian.mdx;
import mondrian.olap.fun.FunUtil;
import mondrian.olap.fun.Resolver;
import mondrian.olap.type.Type;
import mondrian.resource.MondrianResource;
import mondrian.rolap;
import mondrian.spi.UserDefinedFunction;
import mondrian.util;
import org.apache.commons.collections.keyvalue.AbstractMapEntry;
import org.apache.commons.vfs;
import org.apache.commons.vfs.provider.http.HttpFileObject;
import org.apache.log4j.Logger;
import org.eigenbase.xom.XOMUtil;
import org.olap4j.impl.Olap4jUtil;
import org.olap4j.mdx;
import java.io;
import java.lang.ref.Reference;
import java.lang.reflect;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql;
import java.sql.Connection;
import java.util;
import java.util.concurrent;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Util extends XOMUtil{

 public  String nl;

 private  Logger LOGGER;

 public  Object nullValue;

 public  Object EmptyValue;

 private  long databaseMillis;

 private  Random metaRandom;

 public  UUID JVM_INSTANCE_UUID;

 public  boolean PreJdk15;

 public  boolean PreJdk16;

 public  boolean IBM_JVM;

 public  int JdbcVersion;

 public  boolean Retrowoven;

 private  UtilCompatible compatible;

 public  boolean DEBUG;

 private  AtomicInteger counter;

 final  AtomicInteger counter;

 private  Map<String,String> TIME_UNITS;

 final  Iterator<T> iterator;

 private T next;

 private boolean hasNext;

 private List<Pair<String,String>> list;

 private  String s;

 private  int n;

 private  int i;

 private  StringBuilder nameBuf;

 private  StringBuilder valueBuf;

 private  T t0;

 private  T t1;

 private  T t0;

 private  T t1;

 private  T t2;

 private  Iterator<? extends Reference<T>> iterator;

 private  boolean hasNext;

 private  T next;

 private  Functor1 IDENTITY_FUNCTOR;

 private  Functor1 TRUE_FUNCTOR;

 private  Functor1 FALSE_FUNCTOR;

 public  SqlNullSafeComparator instance;

 private  int[] matcher;

 public  byte[] key;

 private  List<K> list;

 private  int pt;

 private  int pt;


public String printMemory(String msg){
    final Runtime rt = Runtime.getRuntime();
    final long freeMemory = rt.freeMemory();
    final long totalMemory = rt.totalMemory();
    final StringBuilder buf = new StringBuilder(64);
    buf.append("FREE_MEMORY:");
    if (msg != null) {
        buf.append(msg);
        buf.append(':');
    }
    buf.append(' ');
    buf.append(freeMemory / 1024);
    buf.append("kb ");
    long hundredths = (freeMemory * 10000) / totalMemory;
    buf.append(hundredths / 100);
    hundredths %= 100;
    if (hundredths >= 10) {
        buf.append('.');
    } else {
        buf.append(".0");
    }
    buf.append(hundredths);
    buf.append('%');
    return buf.toString();
}


public Member getFirstDescendantOnLevel(SchemaReader reader,Member parent,Level level){
    Member m = parent;
    while (m.getLevel() != level) {
        List<Member> children = reader.getMemberChildren(m);
        m = children.get(0);
    }
    return m;
}


public StringBuilder quoteForMdx(StringBuilder buf,String val){
    buf.append("\"");
    String s0 = replace(val, "\"", "\"\"");
    buf.append(s0);
    buf.append("\"");
    return buf;
}


public T deprecated(T reason,boolean fail){
    if (fail) {
        throw new UnsupportedOperationException(reason.toString());
    } else {
        return reason;
    }
}


public Id.Quoting convert(Quoting quoting){
    switch(quoting) {
        case QUOTED:
            return Id.Quoting.QUOTED;
        case UNQUOTED:
            return Id.Quoting.UNQUOTED;
        case KEY:
            return Id.Quoting.KEY;
        default:
            throw Util.unexpected(quoting);
    }
}


public String put(String key,String value){
    for (int i = 0, n = list.size(); i < n; i++) {
        Pair<String, String> pair = list.get(i);
        if (pair.left.equalsIgnoreCase(key)) {
            String old = pair.right;
            if (key.equalsIgnoreCase("Provider")) {
            // Unlike all other properties, later values of
            // "Provider" do not supersede
            } else {
                pair.right = value;
            }
            return old;
        }
    }
    list.add(new Pair<String, String>(key, value));
    return null;
}


public boolean containsValue(Object o){
    if (o == null && size() > 0) {
        return true;
    } else {
        return false;
    }
}


@SuppressWarnings({ "unchecked" })
public List<T> cast(List<?> list){
    return (List<T>) list;
}


public Iterator<K> iterator(){
    return new Iterator<K>() {

        private int pt = -1;

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public K next() {
            return list.get(++pt);
        }

        public boolean hasNext() {
            return pt < list.size();
        }
    };
}


public boolean removeAll(Collection<?> c){
    throw new UnsupportedOperationException();
}


public boolean isValidProperty(String propertyName,Level level){
    return lookupProperty(level, propertyName) != null;
}


public void moveToNext(){
    while (iterator.hasNext()) {
        final Reference<T> ref = iterator.next();
        next = ref.get();
        if (next != null) {
            return;
        }
        iterator.remove();
    }
    hasNext = false;
}


public Quoting toOlap4j(Id.Quoting quoting){
    return Quoting.valueOf(quoting.name());
}


public Random createRandom(long seed){
    if (seed == 0) {
        seed = new Random().nextLong();
        System.out.println("random: seed=" + seed);
    } else if (seed == -1 && metaRandom != null) {
        seed = metaRandom.nextLong();
    }
    return new Random(seed);
}


public Object[] toArray(){
    return new Object[] { t0, t1, t2 };
}


public void assertPostcondition(boolean b,String condition){
    assertTrue(b, condition);
}


public List<Member> addLevelCalculatedMembers(SchemaReader reader,Level level,List<Member> members){
    List<Member> calcMembers = reader.getCalculatedMembers(level.getHierarchy());
    List<Member> calcMembersInThisLevel = new ArrayList<Member>();
    for (Member calcMember : calcMembers) {
        if (calcMember.getLevel().equals(level)) {
            calcMembersInThisLevel.add(calcMember);
        }
    }
    if (!calcMembersInThisLevel.isEmpty()) {
        List<Member> newMemberList = new ConcatenableList<Member>();
        newMemberList.addAll(members);
        newMemberList.addAll(calcMembersInThisLevel);
        return newMemberList;
    }
    return members;
}


public String convertOlap4jConnectStringToNativeMondrian(String url){
    if (url.startsWith("jdbc:mondrian:")) {
        return "Provider=Mondrian; " + url.substring("jdbc:mondrian:".length());
    }
    return null;
}


public String uniquify(String name,int maxLength,Collection<String> nameList){
    assert name != null;
    if (name.length() > maxLength) {
        name = name.substring(0, maxLength);
    }
    if (nameList.contains(name)) {
        String aliasBase = name;
        int j = 0;
        while (true) {
            name = aliasBase + j;
            if (name.length() > maxLength) {
                aliasBase = aliasBase.substring(0, aliasBase.length() - 1);
                continue;
            }
            if (!nameList.contains(name)) {
                break;
            }
            j++;
        }
    }
    nameList.add(name);
    return name;
}


public Query getQuery(){
    return null;
}


public int hashArray(int h,Object[] a){
    // The hashcode for a null array and an empty array should be different
    // than h, so use magic numbers.
    if (a == null) {
        return hash(h, 19690429);
    }
    if (a.length == 0) {
        return hash(h, 19690721);
    }
    for (Object anA : a) {
        h = hash(h, anA);
    }
    return h;
}


public String camelToUpper(String s){
    StringBuilder buf = new StringBuilder(s.length() + 10);
    int prevUpper = -1;
    for (int i = 0; i < s.length(); ++i) {
        char c = s.charAt(i);
        if (Character.isUpperCase(c)) {
            if (i > prevUpper + 1) {
                buf.append('_');
            }
            prevUpper = i;
        } else {
            c = Character.toUpperCase(c);
        }
        buf.append(c);
    }
    return buf.toString();
}


public String readVirtualFileAsString(String catalogUrl){
    InputStream in = readVirtualFile(catalogUrl);
    try {
        final byte[] bytes = Util.readFully(in, 1024);
        return new String(bytes, "utf-8");
    } finally {
        if (in != null) {
            in.close();
        }
    }
}


public boolean hasNext(){
    return pt < list.size();
}


public List<String> parseCommaList(String nameCommaList){
    if (nameCommaList.equals("")) {
        return Collections.emptyList();
    }
    if (nameCommaList.endsWith(",")) {
        // Special treatment for list ending in ",", because split ignores
        // entries after separator.
        final String zzz = "zzz";
        final List<String> list = parseCommaList(nameCommaList + zzz);
        String last = list.get(list.size() - 1);
        if (last.equals(zzz)) {
            list.remove(list.size() - 1);
        } else {
            list.set(list.size() - 1, last.substring(0, last.length() - zzz.length()));
        }
        return list;
    }
    List<String> names = new ArrayList<String>();
    final String[] strings = nameCommaList.split(",");
    for (String string : strings) {
        final int count = names.size();
        if (count > 0 && names.get(count - 1).equals("")) {
            if (count == 1) {
                if (string.equals("")) {
                    names.add("");
                } else {
                    names.set(0, "," + string);
                }
            } else {
                names.set(count - 2, names.get(count - 2) + "," + string);
                names.remove(count - 1);
            }
        } else {
            names.add(string);
        }
    }
    return names;
}


public boolean matches(IdentifierSegment segment,String name){
    switch(segment.getQuoting()) {
        case KEY:
            // FIXME
            return false;
        case QUOTED:
            return equalName(segment.getName(), name);
        case UNQUOTED:
            return segment.getName().equalsIgnoreCase(name);
        default:
            throw unexpected(segment.getQuoting());
    }
}


public void checkCJResultLimit(long resultSize){
    int resultLimit = MondrianProperties.instance().ResultLimit.get();
    // Throw an exeption, if the size of the crossjoin exceeds the result
    // limit.
    if (resultLimit > 0 && resultLimit < resultSize) {
        throw MondrianResource.instance().LimitExceededDuringCrossjoin.ex(resultSize, resultLimit);
    }
    // Throw an exception if the crossjoin exceeds a reasonable limit.
    // (Yes, 4 billion is a reasonable limit.)
    if (resultSize > Integer.MAX_VALUE) {
        throw MondrianResource.instance().LimitExceededDuringCrossjoin.ex(resultSize, Integer.MAX_VALUE);
    }
}


public boolean contains(Object o){
    if (o == null && size() > 0) {
        return true;
    } else {
        return false;
    }
}


public boolean areOccurencesEqual(Collection<T> collection){
    Iterator<T> it = collection.iterator();
    if (!it.hasNext()) {
        // Collection is empty
        return false;
    }
    T first = it.next();
    while (it.hasNext()) {
        T t = it.next();
        if (!t.equals(first)) {
            return false;
        }
    }
    return true;
}


public int size(){
    return list.size();
}


public T safeGet(Future<T> future,String message){
    try {
        return future.get();
    } catch (InterruptedException e) {
        throw newError(e, message);
    } catch (ExecutionException e) {
        final Throwable cause = e.getCause();
        if (cause instanceof RuntimeException) {
            throw (RuntimeException) cause;
        } else if (cause instanceof Error) {
            throw (Error) cause;
        } else {
            throw newError(cause, message);
        }
    }
}


public InputStream readVirtualFile(String url){
    // Treat catalogUrl as an Apache VFS (Virtual File System) URL.
    // VFS handles all of the usual protocols (http:, file:)
    // and then some.
    FileSystemManager fsManager = VFS.getManager();
    if (fsManager == null) {
        throw newError("Cannot get virtual file system manager");
    }
    // Workaround VFS bug.
    if (url.startsWith("file://localhost")) {
        url = url.substring("file://localhost".length());
    }
    if (url.startsWith("file:")) {
        url = url.substring("file:".length());
    }
    // work around for VFS bug not closing http sockets
    // (Mondrian-585)
    if (url.startsWith("http")) {
        try {
            return new URL(url).openStream();
        } catch (IOException e) {
            throw newError("Could not read URL: " + url);
        }
    }
    File userDir = new File("").getAbsoluteFile();
    FileObject file = fsManager.resolveFile(userDir, url);
    FileContent fileContent = null;
    try {
        // Because of VFS caching, make sure we refresh to get the latest
        // file content. This refresh may possibly solve the following
        // workaround for defect MONDRIAN-508, but cannot be tested, so we
        // will leave the work around for now.
        file.refresh();
        // Workaround to defect MONDRIAN-508. For HttpFileObjects, verifies
        // the URL of the file retrieved matches the URL passed in.  A VFS
        // cache bug can cause it to treat URLs with different parameters
        // as the same file (e.g. http://blah.com?param=A,
        // http://blah.com?param=B)
        if (file instanceof HttpFileObject && !file.getName().getURI().equals(url)) {
            fsManager.getFilesCache().removeFile(file.getFileSystem(), file.getName());
            file = fsManager.resolveFile(userDir, url);
        }
        if (!file.isReadable()) {
            throw newError("Virtual file is not readable: " + url);
        }
        fileContent = file.getContent();
    } finally {
        file.close();
    }
    if (fileContent == null) {
        throw newError("Cannot get virtual file content: " + url);
    }
    return fileContent.getInputStream();
}


public ListIterator<T> listIterator(int index){
    return asArrayList().listIterator(index);
}


public boolean isSorted(List<T> list){
    T prev = null;
    for (T t : list) {
        if (prev != null && ((Comparable<T>) prev).compareTo(t) >= 0) {
            return false;
        }
        prev = t;
    }
    return true;
}


public int hash(int h,Object o){
    int k = (o == null) ? 0 : o.hashCode();
    return ((h << 4) | h) ^ k;
}


public int compare(Comparable o1,Comparable o2){
    if (o1 == RolapUtil.sqlNullValue) {
        return -1;
    }
    if (o2 == RolapUtil.sqlNullValue) {
        return 1;
    }
    return o1.compareTo(o2);
}


public Thread newThread(Runnable r){
    final Thread thread = Executors.defaultThreadFactory().newThread(r);
    thread.setDaemon(true);
    thread.setName(name + '_' + counter.incrementAndGet());
    return thread;
}


public long nonDbTimeMillis(){
    final long systemMillis = System.currentTimeMillis();
    return systemMillis - databaseMillis;
}


public String generateUuidString(){
    return compatible.generateUuidString();
}


public String quoteJavaString(String s){
    return s == null ? "null" : "\"" + s.replaceAll("\\\\", "\\\\\\\\").replaceAll("\\\"", "\\\\\"") + "\"";
}


public FunDef getDef(Exp[] args,String name,Syntax syntax){
    // Very simple resolution. Assumes that there is precisely
    // one resolver (i.e. no overloading) and no argument
    // conversions are necessary.
    List<Resolver> resolvers = funTable.getResolvers(name, syntax);
    final Resolver resolver = resolvers.get(0);
    final List<Resolver.Conversion> conversionList = new ArrayList<Resolver.Conversion>();
    final FunDef def = resolver.resolve(args, this, conversionList);
    assert conversionList.isEmpty();
    return def;
}


public Property lookupProperty(Level level,String propertyName){
    do {
        Property[] properties = level.getProperties();
        for (Property property : properties) {
            if (property.getName().equals(propertyName)) {
                return property;
            }
        }
        level = level.getParentLevel();
    } while (level != null);
    // Now try a standard property.
    boolean caseSensitive = MondrianProperties.instance().CaseSensitive.get();
    final Property property = Property.lookup(propertyName, caseSensitive);
    if (property != null && property.isMemberProperty() && property.isStandard()) {
        return property;
    }
    return null;
}


public FunTable getFunTable(){
    return funTable;
}


public long getCommitted()


public void addDatabaseTime(long millis){
    databaseMillis += millis;
}


public int compareIntegers(int i0,int i1){
    return (i0 < i1 ? -1 : (i0 == i1 ? 0 : 1));
}


public List<T> flatList(List<T> t){
    switch(t.size()) {
        case 0:
            return Collections.emptyList();
        case 1:
            return Collections.singletonList(t.get(0));
        case 2:
            return new Flat2List<T>(t.get(0), t.get(1));
        case 3:
            return new Flat3List<T>(t.get(0), t.get(1), t.get(2));
        default:
            // REVIEW: AbstractList contains a modCount field; we could
            // write our own implementation and reduce creation overhead a
            // bit.
            // noinspection unchecked
            return (List<T>) Arrays.asList(t.toArray());
    }
}


public PropertyList parseConnectString(String s){
    return new ConnectStringParser(s).parse();
}


public void assertTrue(boolean b,String message){
    if (!b) {
        throw newInternal("assert failed: " + message);
    }
}


public void cancelStatement(Statement stmt){
    compatible.cancelStatement(stmt);
}


public V get(Object key){
    return null;
}


public Iterable<T> castToIterable(Object iterable){
    if (Util.Retrowoven && !(iterable instanceof Iterable)) {
        return new Iterable<T>() {

            public Iterator<T> iterator() {
                return ((Collection<T>) iterable).iterator();
            }
        };
    }
    return (Iterable<T>) iterable;
}


public List<T> _flatList(T[] t,boolean copy){
    switch(t.length) {
        case 0:
            return Collections.emptyList();
        case 1:
            return Collections.singletonList(t[0]);
        case 2:
            return new Flat2List<T>(t[0], t[1]);
        case 3:
            return new Flat3List<T>(t[0], t[1], t[2]);
        default:
            // REVIEW: AbstractList contains a modCount field; we could
            // write our own implementation and reduce creation overhead a
            // bit.
            if (copy) {
                return Arrays.asList(t.clone());
            } else {
                return Arrays.asList(t);
            }
    }
}


public SQLException close(ResultSet resultSet,Statement statement,Connection connection){
    SQLException firstException = null;
    if (resultSet != null) {
        try {
            if (statement == null) {
                statement = resultSet.getStatement();
            }
            resultSet.close();
        } catch (Throwable t) {
            firstException = new SQLException();
            firstException.initCause(t);
        }
    }
    if (statement != null) {
        try {
            statement.close();
        } catch (Throwable t) {
            if (firstException == null) {
                firstException = new SQLException();
                firstException.initCause(t);
            }
        }
    }
    if (connection != null) {
        try {
            connection.close();
        } catch (Throwable t) {
            if (firstException == null) {
                firstException = new SQLException();
                firstException.initCause(t);
            }
        }
    }
    return firstException;
}


public Member lookupHierarchyRootMember(SchemaReader reader,Hierarchy hierarchy,Id.NameSegment memberName,MatchType matchType){
    // Lookup member at first level.
    // 
    // Don't use access control. Suppose we cannot see the 'nation' level,
    // we still want to be able to resolve '[Customer].[USA].[CA]'.
    List<Member> rootMembers = reader.getHierarchyRootMembers(hierarchy);
    // if doing an inexact search on a non-all hierarchy, create
    // a member corresponding to the name we're searching for so
    // we can use it in a hierarchical search
    Member searchMember = null;
    if (!matchType.isExact() && !hierarchy.hasAll() && !rootMembers.isEmpty()) {
        searchMember = hierarchy.createMember(null, rootMembers.get(0).getLevel(), memberName.name, null);
    }
    int bestMatch = -1;
    int k = -1;
    for (Member rootMember : rootMembers) {
        ++k;
        int rc;
        // when searching on the ALL hierarchy, match must be exact
        if (matchType.isExact() || hierarchy.hasAll()) {
            rc = rootMember.getName().compareToIgnoreCase(memberName.name);
        } else {
            rc = FunUtil.compareSiblingMembers(rootMember, searchMember);
        }
        if (rc == 0) {
            return rootMember;
        }
        if (!hierarchy.hasAll()) {
            if (matchType == MatchType.BEFORE) {
                if (rc < 0 && (bestMatch == -1 || FunUtil.compareSiblingMembers(rootMember, rootMembers.get(bestMatch)) > 0)) {
                    bestMatch = k;
                }
            } else if (matchType == MatchType.AFTER) {
                if (rc > 0 && (bestMatch == -1 || FunUtil.compareSiblingMembers(rootMember, rootMembers.get(bestMatch)) < 0)) {
                    bestMatch = k;
                }
            }
        }
    }
    if (matchType == MatchType.EXACT_SCHEMA) {
        return null;
    }
    if (matchType != MatchType.EXACT && bestMatch != -1) {
        return rootMembers.get(bestMatch);
    }
    // If the first level is 'all', lookup member at second level. For
    // example, they could say '[USA]' instead of '[(All
    // Customers)].[USA]'.
    return (rootMembers.size() > 0 && rootMembers.get(0).isAll()) ? reader.lookupMemberChildByName(rootMembers.get(0), memberName, matchType) : null;
}


public Map<K,V> toNullValuesMap(List<K> list){
    return new NullValuesMap<K, V>(list);
}


public void validate(Formula formula){
}


public Cube getDimensionCube(Dimension dimension){
    final Cube[] cubes = dimension.getSchema().getCubes();
    for (Cube cube : cubes) {
        for (Dimension dimension1 : cube.getDimensions()) {
            // If the dimensions have the same identity,
            // we found an access rule.
            if (dimension == dimension1) {
                return cube;
            }
            // If the passed dimension argument is of class
            // RolapCubeDimension, we must validate the cube
            // assignment and make sure the cubes are the same.
            // If not, skip to the next grant.
            if (dimension instanceof RolapCubeDimension && dimension.equals(dimension1) && !((RolapCubeDimension) dimension1).getCube().equals(cube)) {
                continue;
            }
            // Last thing is to allow for equality correspondences
            // to work with virtual cubes.
            if (cube instanceof RolapCube && ((RolapCube) cube).isVirtual() && dimension.equals(dimension1)) {
                return cube;
            }
        }
    }
    return null;
}


public URL toURL(File file){
    String path = file.getAbsolutePath();
    // This is a bunch of weird code that is required to
    // make a valid URL on the Windows platform, due
    // to inconsistencies in what getAbsolutePath returns.
    String fs = System.getProperty("file.separator");
    if (fs.length() == 1) {
        char sep = fs.charAt(0);
        if (sep != '/') {
            path = path.replace(sep, '/');
        }
        if (path.charAt(0) != '/') {
            path = '/' + path;
        }
    }
    path = "file://" + path;
    return new URL(path);
}


public T[] appendArrays(T[] a0,T[] as){
    int n = a0.length;
    for (T[] a : as) {
        n += a.length;
    }
    T[] copy = Util.copyOf(a0, n);
    n = a0.length;
    for (T[] a : as) {
        System.arraycopy(a, 0, copy, n, a.length);
        n += a.length;
    }
    return copy;
}


public List<T> subList(int fromIndex,int toIndex){
    return asArrayList().subList(fromIndex, toIndex);
}


public Set<T> newIdentityHashSetFake(){
    final HashMap<T, Boolean> map = new HashMap<T, Boolean>();
    return new Set<T>() {

        public int size() {
            return map.size();
        }

        public boolean isEmpty() {
            return map.isEmpty();
        }

        public boolean contains(Object o) {
            return map.containsKey(o);
        }

        public Iterator<T> iterator() {
            return map.keySet().iterator();
        }

        public Object[] toArray() {
            return map.keySet().toArray();
        }

        public <T> T[] toArray(T[] a) {
            return map.keySet().toArray(a);
        }

        public boolean add(T t) {
            return map.put(t, Boolean.TRUE) == null;
        }

        public boolean remove(Object o) {
            return map.remove(o) == Boolean.TRUE;
        }

        public boolean containsAll(Collection<?> c) {
            return map.keySet().containsAll(c);
        }

        public boolean addAll(Collection<? extends T> c) {
            throw new UnsupportedOperationException();
        }

        public boolean retainAll(Collection<?> c) {
            throw new UnsupportedOperationException();
        }

        public boolean removeAll(Collection<?> c) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            map.clear();
        }
    };
}


public T set(int index,Object element){
    throw new UnsupportedOperationException();
}


public String parseName(){
    nameBuf.setLength(0);
    while (true) {
        char c = s.charAt(i);
        switch(c) {
            case '=':
                i++;
                if (i < n && (c = s.charAt(i)) == '=') {
                    // doubled equals sign; take one of them, and carry on
                    i++;
                    nameBuf.append(c);
                    break;
                }
                String name = nameBuf.toString();
                name = name.trim();
                return name;
            case ' ':
                if (nameBuf.length() == 0) {
                    // ignore preceding spaces
                    i++;
                    if (i >= n) {
                        // there is no name, e.g. trailing spaces after
                        // semicolon, 'x=1; y=2; '
                        return null;
                    }
                    break;
                } else {
                // fall through
                }
            default:
                nameBuf.append(c);
                i++;
                if (i >= n) {
                    return nameBuf.toString().trim();
                }
        }
    }
}


public boolean containsAll(Collection<?> c){
    Iterator<?> e = c.iterator();
    while (e.hasNext()) {
        if (!contains(e.next())) {
            return false;
        }
    }
    return true;
}


public T[] copyOf(U[] original,int newLength,Class<? extends T[]> newType){
    @SuppressWarnings({ "unchecked", "RedundantCast" })
    T[] copy = ((Object) newType == (Object) Object[].class) ? (T[]) new Object[newLength] : (T[]) Array.newInstance(newType.getComponentType(), newLength);
    // noinspection SuspiciousSystemArraycopy
    System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
    return copy;
}


public Iterable<T> filter(Iterable<T> iterable,Functor1<Boolean,T> conds){
    final Functor1<Boolean, T>[] conds2 = optimizeConditions(conds);
    if (conds2.length == 0) {
        return iterable;
    }
    return new Iterable<T>() {

        public Iterator<T> iterator() {
            return new Iterator<T>() {

                final Iterator<T> iterator = iterable.iterator();

                T next;

                boolean hasNext = moveToNext();

                private boolean moveToNext() {
                    outer: while (iterator.hasNext()) {
                        next = iterator.next();
                        for (Functor1<Boolean, T> cond : conds2) {
                            if (!cond.apply(next)) {
                                continue outer;
                            }
                        }
                        return true;
                    }
                    return false;
                }

                public boolean hasNext() {
                    return hasNext;
                }

                public T next() {
                    T t = next;
                    hasNext = moveToNext();
                    return t;
                }

                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    };
}


public boolean requiresExpression(){
    return false;
}


public String makeFqName(String parentUniqueName,String name){
    if (parentUniqueName == null) {
        return quoteMdxIdentifier(name);
    } else {
        StringBuilder buf = new StringBuilder(64);
        buf.append(parentUniqueName);
        buf.append('.');
        Util.quoteMdxIdentifier(name, buf);
        return buf.toString();
    }
}


@SuppressWarnings({ "unchecked" })
public T[] genericArray(Class<T> clazz,int size){
    return (T[]) Array.newInstance(clazz, size);
}


public boolean addAll(int index,Collection<? extends T> c){
    throw new UnsupportedOperationException();
}


public boolean isNull(Object o){
    return o == null || o == nullValue;
}


public boolean equals(Object o){
    if (o instanceof Flat3List) {
        Flat3List that = (Flat3List) o;
        return Util.equals(this.t0, that.t0) && Util.equals(this.t1, that.t1) && Util.equals(this.t2, that.t2);
    }
    return o.equals(this);
}


public Map<String,String> toMap(Properties properties){
    return new AbstractMap<String, String>() {

        @SuppressWarnings({ "unchecked" })
        public Set<Entry<String, String>> entrySet() {
            return (Set) properties.entrySet();
        }
    };
}


public int compareName(String s,String t){
    boolean caseSensitive = MondrianProperties.instance().CaseSensitive.get();
    return caseSensitive ? s.compareTo(t) : s.compareToIgnoreCase(t);
}


public Functor1<Boolean,T>[] optimizeConditions(Functor1<Boolean,T>[] conds){
    final List<Functor1<Boolean, T>> functor1List = new ArrayList<Functor1<Boolean, T>>(Arrays.asList(conds));
    for (Iterator<Functor1<Boolean, T>> funcIter = functor1List.iterator(); funcIter.hasNext(); ) {
        Functor1<Boolean, T> booleanTFunctor1 = funcIter.next();
        if (booleanTFunctor1 == trueFunctor()) {
            funcIter.remove();
        }
    }
    if (functor1List.size() < conds.length) {
        // noinspection unchecked
        return functor1List.toArray(new Functor1[functor1List.size()]);
    } else {
        return conds;
    }
}


public String toString(){
    return "[" + t0 + ", " + t1 + ", " + t2 + "]";
}


public String parseValue(){
    char c;
    // skip over leading white space
    while ((c = s.charAt(i)) == ' ') {
        i++;
        if (i >= n) {
            return "";
        }
    }
    if (c == '"' || c == '\'') {
        String value = parseQuoted(c);
        // skip over trailing white space
        while (i < n && (c = s.charAt(i)) == ' ') {
            i++;
        }
        if (i >= n) {
            return value;
        } else if (s.charAt(i) == ';') {
            i++;
            return value;
        } else {
            throw new RuntimeException("quoted value ended too soon, at position " + i + " in '" + s + "'");
        }
    } else {
        String value;
        int semi = s.indexOf(';', i);
        if (semi >= 0) {
            value = s.substring(i, semi);
            i = semi + 1;
        } else {
            value = s.substring(i);
            i = n;
        }
        return value.trim();
    }
}


public URL getClosestResource(ClassLoader classLoader,String name){
    URL resource = null;
    try {
        // The last resource will be from the nearest ClassLoader.
        Enumeration<URL> resourceCandidates = classLoader.getResources(name);
        while (resourceCandidates.hasMoreElements()) {
            resource = resourceCandidates.nextElement();
        }
    } catch (IOException ioe) {
        // ignore exception - it's OK if file is not found
        // just keep getResource contract and return null
        Util.discard(ioe);
    }
    return resource;
}


public List<Id.Segment> parseIdentifier(String s){
    return convert(org.olap4j.impl.IdentifierParser.parseIdentifier(s));
}


public boolean retainAll(Collection<?> c){
    throw new UnsupportedOperationException();
}


public Exp createExpr(OlapElement element){
    if (element instanceof Member) {
        Member member = (Member) element;
        return new MemberExpr(member);
    } else if (element instanceof Level) {
        Level level = (Level) element;
        return new LevelExpr(level);
    } else if (element instanceof Hierarchy) {
        Hierarchy hierarchy = (Hierarchy) element;
        return new HierarchyExpr(hierarchy);
    } else if (element instanceof Dimension) {
        Dimension dimension = (Dimension) element;
        return new DimensionExpr(dimension);
    } else if (element instanceof NamedSet) {
        NamedSet namedSet = (NamedSet) element;
        return new NamedSetExpr(namedSet);
    } else {
        throw Util.newInternal("Unexpected element type: " + element);
    }
}


public byte[] digestMd5(String value){
    final MessageDigest algorithm;
    try {
        algorithm = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
    }
    return algorithm.digest(value.getBytes());
}


public StringBuilder replace(StringBuilder buf,int start,String find,String replace){
    // Search and replace from the end towards the start, to avoid O(n ^ 2)
    // copying if the string occurs very commonly.
    int findLength = find.length();
    if (findLength == 0) {
        // Special case where the seek string is empty.
        for (int j = buf.length(); j >= 0; --j) {
            buf.insert(j, replace);
        }
        return buf;
    }
    int k = buf.length();
    while (k > 0) {
        int i = buf.lastIndexOf(find, k);
        if (i < start) {
            break;
        }
        buf.replace(i, i + find.length(), replace);
        // Step back far enough to ensure that the beginning of the section
        // we just replaced does not cause a match.
        k = i - findLength;
    }
    return buf;
}


public String commaList(String s,List<T> list){
    final StringBuilder buf = new StringBuilder(s);
    buf.append("(");
    int k = -1;
    for (T t : list) {
        if (++k > 0) {
            buf.append(", ");
        }
        buf.append(t);
    }
    buf.append(")");
    return buf.toString();
}


public T getMatchingCause(Throwable e,Class<T> clazz){
    for (; ; ) {
        if (clazz.isInstance(e)) {
            return clazz.cast(e);
        }
        final Throwable cause = e.getCause();
        if (cause == null || cause == e) {
            return null;
        }
        e = cause;
    }
}


public String mdxEncodeString(String st){
    StringBuilder retString = new StringBuilder(st.length() + 20);
    for (int i = 0; i < st.length(); i++) {
        char c = st.charAt(i);
        if ((c == ']') && ((i + 1) < st.length()) && (st.charAt(i + 1) != '.')) {
            // escaping character
            retString.append(']');
        }
        retString.append(c);
    }
    return retString.toString();
}


public RuntimeException badValue(Enum<T> anEnum){
    return Util.newInternal("Was not expecting value '" + anEnum + "' for enumeration '" + anEnum.getDeclaringClass().getName() + "' in this context");
}


public int compareKey(Object k1,Object k2){
    if (k1 instanceof Boolean) {
        // Luckily, "F" comes before "T" in the alphabet.
        k1 = k1.toString();
        k2 = k2.toString();
    }
    return ((Comparable) k1).compareTo(k2);
}


public boolean canCast(Collection<?> collection,Class<T> clazz){
    for (Object o : collection) {
        if (o != null && !clazz.isInstance(o)) {
            return false;
        }
    }
    return true;
}


public int[] compile(byte[] key){
    int[] matcher = new int[key.length];
    int j = 0;
    for (int i = 1; i < key.length; i++) {
        while (j > 0 && key[j] != key[i]) {
            j = matcher[j - 1];
        }
        if (key[i] == key[j]) {
            j++;
        }
        matcher[i] = j;
    }
    return matcher;
}


public RuntimeException newInternal(Throwable e,String message){
    return MondrianResource.instance().Internal.ex(message, e);
}


public String normalizeName(String s){
    return MondrianProperties.instance().CaseSensitive.get() ? s : s.toUpperCase();
}


public void parsePair(PropertyList list){
    String name = parseName();
    if (name == null) {
        return;
    }
    String value;
    if (i >= n) {
        value = "";
    } else if (s.charAt(i) == ';') {
        i++;
        value = "";
    } else {
        value = parseValue();
    }
    list.put(name, value);
}


public byte[] readFully(InputStream in,int bufferSize){
    if (bufferSize <= 0) {
        throw new IllegalArgumentException("Buffer size must be greater than 0");
    }
    final byte[] buffer = new byte[bufferSize];
    final ByteArrayOutputStream baos = new ByteArrayOutputStream(bufferSize);
    int len;
    while ((len = in.read(buffer)) != -1) {
        baos.write(buffer, 0, len);
    }
    return baos.toByteArray();
}


public T only(List<T> list){
    if (list.size() != 1) {
        throw new IndexOutOfBoundsException("list " + list + " has " + list.size() + " elements, expected 1");
    }
    return list.get(0);
}


public String readURL(URL url,Map<String,String> map){
    final Reader r = new BufferedReader(new InputStreamReader(url.openStream()));
    final int BUF_SIZE = 8096;
    try {
        String xmlCatalog = readFully(r, BUF_SIZE);
        xmlCatalog = Util.replaceProperties(xmlCatalog, map);
        return xmlCatalog;
    } finally {
        r.close();
    }
}


public BigDecimal makeBigDecimalFromDouble(double d){
    return compatible.makeBigDecimalFromDouble(d);
}


public int indexOf(Object o){
    if (t0.equals(o)) {
        return 0;
    }
    if (t1.equals(o)) {
        return 1;
    }
    if (t2.equals(o)) {
        return 2;
    }
    return -1;
}


public ScheduledExecutorService getScheduledExecutorService(int maxNbThreads,String name){
    return Executors.newScheduledThreadPool(maxNbThreads, new ThreadFactory() {

        final AtomicInteger counter = new AtomicInteger(0);

        public Thread newThread(Runnable r) {
            final Thread thread = Executors.defaultThreadFactory().newThread(r);
            thread.setDaemon(true);
            thread.setName(name + '_' + counter.incrementAndGet());
            return thread;
        }
    });
}


public RuntimeException needToImplement(Object o){
    throw new UnsupportedOperationException("need to implement " + o);
}


public Role createRootRole(Schema schema){
    RoleImpl role = new RoleImpl();
    role.grant(schema, Access.ALL);
    role.makeImmutable();
    return role;
}


public void add(int index,Object element){
    throw new UnsupportedOperationException();
}


public List<T> asArrayList(){
    // noinspection unchecked
    return Arrays.asList((T[]) toArray());
}


public void quoteMdxIdentifier(List<Id.Segment> ids,StringBuilder sb){
    for (int i = 0; i < ids.size(); i++) {
        if (i > 0) {
            sb.append('.');
        }
        ids.get(i).toString(sb);
    }
}


public long dbTimeMillis(){
    return databaseMillis;
}


public int getMemberOrdinalInParent(SchemaReader reader,Member member){
    Member parent = member.getParentMember();
    List<Member> siblings = (parent == null) ? reader.getHierarchyRootMembers(member.getHierarchy()) : reader.getMemberChildren(parent);
    for (int i = 0; i < siblings.size(); i++) {
        if (siblings.get(i).equals(member)) {
            return i;
        }
    }
    throw Util.newInternal("could not find member " + member + " amongst its siblings");
}


public T last(List<T> list){
    return list.get(list.size() - 1);
}


public ExecutorService getExecutorService(int maximumPoolSize,int corePoolSize,long keepAliveTime,String name,RejectedExecutionHandler rejectionPolicy){
    if (Util.PreJdk16) {
        // On JDK1.5, if you specify corePoolSize=0, nothing gets executed.
        // Bummer.
        corePoolSize = Math.max(corePoolSize, 1);
    }
    // We must create a factory where the threads
    // have the right name and are marked as daemon threads.
    final ThreadFactory factory = new ThreadFactory() {

        private final AtomicInteger counter = new AtomicInteger(0);

        public Thread newThread(Runnable r) {
            final Thread t = Executors.defaultThreadFactory().newThread(r);
            t.setDaemon(true);
            t.setName(name + '_' + counter.incrementAndGet());
            return t;
        }
    };
    // Ok, create the executor
    final ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize > 0 ? maximumPoolSize : Integer.MAX_VALUE, keepAliveTime, TimeUnit.SECONDS, // we use a sync queue. any other type of queue
    // will prevent the tasks from running concurrently
    // because the executors API requires blocking queues.
    // Important to pass true here. This makes the
    // order of tasks deterministic.
    // TODO Write a non-blocking queue which implements
    // the blocking queue API so we can pass that to the
    // executor.
    new SynchronousQueue<Runnable>(true), factory);
    // Set the rejection policy if required.
    if (rejectionPolicy != null) {
        executor.setRejectedExecutionHandler(rejectionPolicy);
    }
    // Done
    return executor;
}


public Set<Entry<K,V>> entrySet(){
    return new AbstractSet<Entry<K, V>>() {

        public Iterator<Entry<K, V>> iterator() {
            return new Iterator<Entry<K, V>>() {

                private int pt = -1;

                public void remove() {
                    throw new UnsupportedOperationException();
                }

                @SuppressWarnings("unchecked")
                public Entry<K, V> next() {
                    return new AbstractMapEntry(list.get(++pt), null) {
                    };
                }

                public boolean hasNext() {
                    return pt < list.size();
                }
            };
        }

        public int size() {
            return list.size();
        }

        public boolean contains(Object o) {
            if (o instanceof Entry) {
                if (list.contains(((Entry) o).getKey())) {
                    return true;
                }
            }
            return false;
        }
    };
}


public boolean alwaysResolveFunDef(){
    return false;
}


public long getMax()


public boolean containsKey(Object key){
    return list.contains(key);
}


public Functor1<Boolean,PT> falseFunctor(){
    // noinspection unchecked
    return (Functor1) FALSE_FUNCTOR;
}


public List<T> sort(Collection<T> collection,Comparator<T> comparator){
    Object[] a = collection.toArray(new Object[collection.size()]);
    // noinspection unchecked
    Arrays.sort(a, (Comparator<? super Object>) comparator);
    return cast(Arrays.asList(a));
}


public Set<T> newIdentityHashSet(){
    return compatible.newIdentityHashSet();
}


public UserDefinedFunction createUdf(Class<? extends UserDefinedFunction> udfClass,String functionName){
    // Instantiate class with default constructor.
    UserDefinedFunction udf;
    String className = udfClass.getName();
    String functionNameOrEmpty = functionName == null ? "" : functionName;
    // Find a constructor.
    Constructor<?> constructor;
    Object[] args = {};
    // 0. Check that class is public and top-level or static.
    // Before JDK 1.5, inner classes are impossible; retroweaver cannot
    // handle the getEnclosingClass method, so skip the check.
    if (!Modifier.isPublic(udfClass.getModifiers()) || (!PreJdk15 && udfClass.getEnclosingClass() != null && !Modifier.isStatic(udfClass.getModifiers()))) {
        throw MondrianResource.instance().UdfClassMustBePublicAndStatic.ex(functionName, className);
    }
    // 1. Look for a constructor "public Udf(String name)".
    try {
        constructor = udfClass.getConstructor(String.class);
        if (Modifier.isPublic(constructor.getModifiers())) {
            args = new Object[] { functionName };
        } else {
            constructor = null;
        }
    } catch (NoSuchMethodException e) {
        constructor = null;
    }
    // 2. Otherwise, look for a constructor "public Udf()".
    if (constructor == null) {
        try {
            constructor = udfClass.getConstructor();
            if (Modifier.isPublic(constructor.getModifiers())) {
                args = new Object[] {};
            } else {
                constructor = null;
            }
        } catch (NoSuchMethodException e) {
            constructor = null;
        }
    }
    // 3. Else, no constructor suitable.
    if (constructor == null) {
        throw MondrianResource.instance().UdfClassWrongIface.ex(functionNameOrEmpty, className, UserDefinedFunction.class.getName());
    }
    // Instantiate class.
    try {
        udf = (UserDefinedFunction) constructor.newInstance(args);
    } catch (InstantiationException e) {
        throw MondrianResource.instance().UdfClassWrongIface.ex(functionNameOrEmpty, className, UserDefinedFunction.class.getName());
    } catch (IllegalAccessException e) {
        throw MondrianResource.instance().UdfClassWrongIface.ex(functionName, className, UserDefinedFunction.class.getName());
    } catch (ClassCastException e) {
        throw MondrianResource.instance().UdfClassWrongIface.ex(functionNameOrEmpty, className, UserDefinedFunction.class.getName());
    } catch (InvocationTargetException e) {
        throw MondrianResource.instance().UdfClassWrongIface.ex(functionName, className, UserDefinedFunction.class.getName());
    }
    return udf;
}


public void threadLocalRemove(ThreadLocal<T> threadLocal){
    compatible.threadLocalRemove(threadLocal);
}


public T getAnnotation(Method method,String annotationClassName,T defaultValue){
    return compatible.getAnnotation(method, annotationClassName, defaultValue);
}


public long getUsed()


public Level lookupHierarchyLevel(Hierarchy hierarchy,String s){
    final Level[] levels = hierarchy.getLevels();
    for (Level level : levels) {
        if (level.getName().equalsIgnoreCase(s)) {
            return level;
        }
    }
    return null;
}


public Cube lookupCube(SchemaReader schemaReader,String cubeName,boolean fail){
    for (Cube cube : schemaReader.getCubes()) {
        if (Util.compareName(cube.getName(), cubeName) == 0) {
            return cube;
        }
    }
    if (fail) {
        throw MondrianResource.instance().MdxCubeNotFound.ex(cubeName);
    }
    return null;
}


public T compileScript(Class<T> iface,String script,String engineName){
    return compatible.compileScript(iface, script, engineName);
}


public Parameter createOrLookupParam(boolean definition,String name,Type type,Exp defaultExp,String description){
    return null;
}


public Locale parseLocale(String localeString){
    String[] strings = localeString.split("_");
    switch(strings.length) {
        case 1:
            return new Locale(strings[0]);
        case 2:
            return new Locale(strings[0], strings[1]);
        case 3:
            return new Locale(strings[0], strings[1], strings[2]);
        default:
            throw newInternal("bad locale string '" + localeString + "'");
    }
}


public Validator createSimpleValidator(FunTable funTable){
    return new Validator() {

        public Query getQuery() {
            return null;
        }

        public SchemaReader getSchemaReader() {
            throw new UnsupportedOperationException();
        }

        public Exp validate(Exp exp, boolean scalar) {
            return exp;
        }

        public void validate(ParameterExpr parameterExpr) {
        }

        public void validate(MemberProperty memberProperty) {
        }

        public void validate(QueryAxis axis) {
        }

        public void validate(Formula formula) {
        }

        public FunDef getDef(Exp[] args, String name, Syntax syntax) {
            // Very simple resolution. Assumes that there is precisely
            // one resolver (i.e. no overloading) and no argument
            // conversions are necessary.
            List<Resolver> resolvers = funTable.getResolvers(name, syntax);
            final Resolver resolver = resolvers.get(0);
            final List<Resolver.Conversion> conversionList = new ArrayList<Resolver.Conversion>();
            final FunDef def = resolver.resolve(args, this, conversionList);
            assert conversionList.isEmpty();
            return def;
        }

        public boolean alwaysResolveFunDef() {
            return false;
        }

        public boolean canConvert(int ordinal, Exp fromExp, int to, List<Resolver.Conversion> conversions) {
            return true;
        }

        public boolean requiresExpression() {
            return false;
        }

        public FunTable getFunTable() {
            return funTable;
        }

        public Parameter createOrLookupParam(boolean definition, String name, Type type, Exp defaultExp, String description) {
            return null;
        }
    };
}


public K next(){
    return list.get(++pt);
}


public boolean canConvert(int ordinal,Exp fromExp,int to,List<Resolver.Conversion> conversions){
    return true;
}


public Functor1<T,T> identityFunctor(){
    // noinspection unchecked
    return (Functor1) IDENTITY_FUNCTOR;
}


public RuntimeException unexpected(Enum value){
    return Util.newInternal("Was not expecting value '" + value + "' for enumeration '" + value.getClass().getName() + "' in this context");
}


public String quotePattern(String s){
    return compatible.quotePattern(s);
}


public Collection<V> values(){
    return new AbstractList<V>() {

        public V get(int index) {
            return null;
        }

        public int size() {
            return list.size();
        }

        public boolean contains(Object o) {
            if (o == null && size() > 0) {
                return true;
            } else {
                return false;
            }
        }
    };
}


public MemoryInfo getMemoryInfo(){
    return compatible.getMemoryInfo();
}


public Pair<Long,TimeUnit> parseInterval(String s,TimeUnit unit){
    final String original = s;
    for (Map.Entry<String, String> entry : TIME_UNITS.entrySet()) {
        final String abbrev = entry.getKey();
        if (s.endsWith(abbrev)) {
            final String full = entry.getValue();
            try {
                unit = TimeUnit.valueOf(full);
                s = s.substring(0, s.length() - abbrev.length());
                break;
            } catch (IllegalArgumentException e) {
            // ignore - MINUTES, HOURS, DAYS are not defined in JDK1.5
            }
        }
    }
    if (unit == null) {
        throw new NumberFormatException("Invalid time interval '" + original + "'. Does not contain a " + "time unit. (Suffix may be ns (nanoseconds), " + "us (microseconds), ms (milliseconds), s (seconds), " + "h (hours), d (days). For example, '20s' means 20 seconds.)");
    }
    try {
        return Pair.of(new BigDecimal(s).longValue(), unit);
    } catch (NumberFormatException e) {
        throw new NumberFormatException("Invalid time interval '" + original + "'");
    }
}


public boolean isBlank(String str){
    final int strLen;
    if (str == null || (strLen = str.length()) == 0) {
        return true;
    }
    for (int i = 0; i < strLen; i++) {
        if (!Character.isWhitespace(str.charAt(i))) {
            return false;
        }
    }
    return true;
}


public byte[] digestSha256(String value){
    final MessageDigest algorithm;
    try {
        algorithm = MessageDigest.getInstance("SHA-256");
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
    }
    return algorithm.digest(value.getBytes());
}


public int binarySearch(T[] ts,int start,int end,T t){
    return compatible.binarySearch(ts, start, end, t);
}


public void remove(){
    throw new UnsupportedOperationException();
}


public String parseQuoted(char q){
    char c = s.charAt(i++);
    Util.assertTrue(c == q);
    valueBuf.setLength(0);
    while (i < n) {
        c = s.charAt(i);
        if (c == q) {
            i++;
            if (i < n) {
                c = s.charAt(i);
                if (c == q) {
                    valueBuf.append(c);
                    i++;
                    continue;
                }
            }
            return valueBuf.toString();
        } else {
            valueBuf.append(c);
            i++;
        }
    }
    throw new RuntimeException("Connect string '" + s + "' contains unterminated quoted value '" + valueBuf.toString() + "'");
}


public int hashCode(){
    int h = 1;
    h = h * 31 + t0.hashCode();
    h = h * 31 + t1.hashCode();
    h = h * 31 + t2.hashCode();
    return h;
}


public String implode(List<Id.Segment> names){
    StringBuilder sb = new StringBuilder(64);
    for (int i = 0; i < names.size(); i++) {
        if (i > 0) {
            sb.append(".");
        }
        // FIXME: should be:
        // names.get(i).toString(sb);
        // but that causes some tests to fail
        Id.Segment segment = names.get(i);
        switch(segment.getQuoting()) {
            case UNQUOTED:
                segment = new Id.NameSegment(((Id.NameSegment) segment).name);
        }
        segment.toString(sb);
    }
    return sb.toString();
}


public Set<K> keySet(){
    return new AbstractSet<K>() {

        public Iterator<K> iterator() {
            return new Iterator<K>() {

                private int pt = -1;

                public void remove() {
                    throw new UnsupportedOperationException();
                }

                public K next() {
                    return list.get(++pt);
                }

                public boolean hasNext() {
                    return pt < list.size();
                }
            };
        }

        public int size() {
            return list.size();
        }

        public boolean contains(Object o) {
            return list.contains(o);
        }
    };
}


public Iterable<T2> over(Iterable<? extends Reference<T2>> referenceIterable){
    return new Iterable<T2>() {

        public Iterator<T2> iterator() {
            return new GcIterator<T2>(referenceIterable.iterator());
        }
    };
}


public E lookup(Class<E> clazz,String name,E defaultValue){
    if (name == null) {
        return defaultValue;
    }
    try {
        return Enum.valueOf(clazz, name);
    } catch (IllegalArgumentException e) {
        return defaultValue;
    }
}


public RuntimeException newError(Throwable e,String message){
    return newInternal(e, message);
}


public String wildcardToRegexp(List<String> wildcards){
    StringBuilder buf = new StringBuilder();
    for (String value : wildcards) {
        if (buf.length() > 0) {
            buf.append('|');
        }
        int i = 0;
        while (true) {
            int percent = value.indexOf('%', i);
            int underscore = value.indexOf('_', i);
            if (percent == -1 && underscore == -1) {
                if (i < value.length()) {
                    buf.append(quotePattern(value.substring(i)));
                }
                break;
            }
            if (underscore >= 0 && (underscore < percent || percent < 0)) {
                if (i < underscore) {
                    buf.append(quotePattern(value.substring(i, underscore)));
                }
                buf.append('.');
                i = underscore + 1;
            } else if (percent >= 0 && (percent < underscore || underscore < 0)) {
                if (i < percent) {
                    buf.append(quotePattern(value.substring(i, percent)));
                }
                buf.append(".*");
                i = percent + 1;
            } else {
                throw new IllegalArgumentException();
            }
        }
    }
    return buf.toString();
}


public SortedSet<E> intersect(SortedSet<E> set1,SortedSet<E> set2){
    if (set1.isEmpty()) {
        return set1;
    }
    if (set2.isEmpty()) {
        return set2;
    }
    if (!(set1 instanceof ArraySortedSet) || !(set2 instanceof ArraySortedSet)) {
        final TreeSet<E> set = new TreeSet<E>(set1);
        set.retainAll(set2);
        return set;
    }
    final Comparable<?>[] result = new Comparable[Math.min(set1.size(), set2.size())];
    final Iterator<E> it1 = set1.iterator();
    final Iterator<E> it2 = set2.iterator();
    int i = 0;
    E e1 = it1.next();
    E e2 = it2.next();
    for (; ; ) {
        final int compare = e1.compareTo(e2);
        if (compare == 0) {
            result[i++] = e1;
            if (!it1.hasNext() || !it2.hasNext()) {
                break;
            }
            e1 = it1.next();
            e2 = it2.next();
        } else if (compare == 1) {
            if (!it2.hasNext()) {
                break;
            }
            e2 = it2.next();
        } else {
            if (!it1.hasNext()) {
                break;
            }
            e1 = it1.next();
        }
    }
    return new ArraySortedSet(result, 0, i);
}


public boolean equalName(String s,String t){
    if (s == null) {
        return t == null;
    }
    boolean caseSensitive = MondrianProperties.instance().CaseSensitive.get();
    return caseSensitive ? s.equals(t) : s.equalsIgnoreCase(t);
}


public OlapElement lookupCompound(SchemaReader schemaReader,OlapElement parent,List<Id.Segment> names,boolean failIfNotFound,int category,MatchType matchType){
    Util.assertPrecondition(parent != null, "parent != null");
    if (LOGGER.isDebugEnabled()) {
        StringBuilder buf = new StringBuilder(64);
        buf.append("Util.lookupCompound: ");
        buf.append("parent.name=");
        buf.append(parent.getName());
        buf.append(", category=");
        buf.append(Category.instance.getName(category));
        buf.append(", names=");
        quoteMdxIdentifier(names, buf);
        LOGGER.debug(buf.toString());
    }
    // First look up a member from the cache of calculated members
    // (cubes and queries both have them).
    switch(category) {
        case Category.Member:
        case Category.Unknown:
            Member member = schemaReader.getCalculatedMember(names);
            if (member != null) {
                return member;
            }
    }
    // Likewise named set.
    switch(category) {
        case Category.Set:
        case Category.Unknown:
            NamedSet namedSet = schemaReader.getNamedSet(names);
            if (namedSet != null) {
                return namedSet;
            }
    }
    // Now resolve the name one part at a time.
    for (int i = 0; i < names.size(); i++) {
        OlapElement child;
        Id.NameSegment name;
        if (names.get(i) instanceof Id.NameSegment) {
            name = (Id.NameSegment) names.get(i);
            child = schemaReader.getElementChild(parent, name, matchType);
        } else if (parent instanceof RolapLevel && names.get(i) instanceof Id.KeySegment && names.get(i).getKeyParts().size() == 1) {
            // The following code is for SsasCompatibleNaming=false.
            // Continues the very limited support for key segments in
            // mondrian-3.x. To be removed in mondrian-4, when
            // SsasCompatibleNaming=true is the only option.
            final Id.KeySegment keySegment = (Id.KeySegment) names.get(i);
            name = keySegment.getKeyParts().get(0);
            final List<Member> levelMembers = schemaReader.getLevelMembers((Level) parent, false);
            child = null;
            for (Member member : levelMembers) {
                if (((RolapMember) member).getKey().toString().equals(name.getName())) {
                    child = member;
                    break;
                }
            }
        } else {
            name = null;
            child = schemaReader.getElementChild(parent, name, matchType);
        }
        // if we're doing a non-exact match, and we find a non-exact
        // match, then for an after match, return the first child
        // of each subsequent level; for a before match, return the
        // last child
        if (child instanceof Member && !matchType.isExact() && !Util.equalName(child.getName(), name.getName())) {
            Member bestChild = (Member) child;
            for (int j = i + 1; j < names.size(); j++) {
                List<Member> childrenList = schemaReader.getMemberChildren(bestChild);
                FunUtil.hierarchizeMemberList(childrenList, false);
                if (matchType == MatchType.AFTER) {
                    bestChild = childrenList.get(0);
                } else {
                    bestChild = childrenList.get(childrenList.size() - 1);
                }
                if (bestChild == null) {
                    child = null;
                    break;
                }
            }
            parent = bestChild;
            break;
        }
        if (child == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Util.lookupCompound: " + "parent.name=" + parent.getName() + " has no child with name=" + name);
            }
            if (!failIfNotFound) {
                return null;
            } else if (category == Category.Member) {
                throw MondrianResource.instance().MemberNotFound.ex(quoteMdxIdentifier(names));
            } else {
                throw MondrianResource.instance().MdxChildObjectNotFound.ex(name.toString(), parent.getQualifiedName());
            }
        }
        parent = child;
        if (matchType == MatchType.EXACT_SCHEMA) {
            matchType = MatchType.EXACT;
        }
    }
    if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("Util.lookupCompound: " + "found child.name=" + parent.getName() + ", child.class=" + parent.getClass().getName());
    }
    switch(category) {
        case Category.Dimension:
            if (parent instanceof Dimension) {
                return parent;
            } else if (parent instanceof Hierarchy) {
                return parent.getDimension();
            } else if (failIfNotFound) {
                throw Util.newError("Can not find dimension '" + implode(names) + "'");
            } else {
                return null;
            }
        case Category.Hierarchy:
            if (parent instanceof Hierarchy) {
                return parent;
            } else if (parent instanceof Dimension) {
                return parent.getHierarchy();
            } else if (failIfNotFound) {
                throw Util.newError("Can not find hierarchy '" + implode(names) + "'");
            } else {
                return null;
            }
        case Category.Level:
            if (parent instanceof Level) {
                return parent;
            } else if (failIfNotFound) {
                throw Util.newError("Can not find level '" + implode(names) + "'");
            } else {
                return null;
            }
        case Category.Member:
            if (parent instanceof Member) {
                return parent;
            } else if (failIfNotFound) {
                throw MondrianResource.instance().MdxCantFindMember.ex(implode(names));
            } else {
                return null;
            }
        case Category.Unknown:
            assertPostcondition(parent != null, "return != null");
            return parent;
        default:
            throw newInternal("Bad switch " + category);
    }
}


public Boolean apply(Object param){
    return false;
}


public String[] convertStackToString(Throwable e){
    List<String> list = new ArrayList<String>();
    while (e != null) {
        String sMsg = getErrorMessage(e);
        list.add(sMsg);
        e = e.getCause();
    }
    return list.toArray(new String[list.size()]);
}


public boolean isEmpty(){
    return false;
}


public void clear(){
    throw new UnsupportedOperationException();
}


public int match(byte[] a){
    int j = 0;
    for (int i = 0; i < a.length; i++) {
        while (j > 0 && key[j] != a[i]) {
            j = matcher[j - 1];
        }
        if (a[i] == key[j]) {
            j++;
        }
        if (key.length == j) {
            return i - key.length + 1;
        }
    }
    return -1;
}


public PropertyList parse(){
    PropertyList list = new PropertyList();
    while (i < n) {
        parsePair(list);
    }
    return list;
}


public List<T> flatListCopy(T t){
    return _flatList(t, true);
}


public int caseSensitiveCompareName(String s,String t){
    boolean caseSensitive = MondrianProperties.instance().CaseSensitive.get();
    if (caseSensitive) {
        return s.compareTo(t);
    } else {
        int v = s.compareToIgnoreCase(t);
        // if ignore case returns 0 compare in a case sensitive manner
        // this was introduced to solve an issue with Member.equals()
        // and Member.compareTo() not agreeing with each other
        return v == 0 ? s.compareTo(t) : v;
    }
}


public Functor1<Boolean,PT> trueFunctor(){
    // noinspection unchecked
    return (Functor1) TRUE_FUNCTOR;
}


public boolean equal(String s,String t,boolean matchCase){
    return matchCase ? s.equals(t) : s.equalsIgnoreCase(t);
}


public void assertPrecondition(boolean b,String condition){
    assertTrue(b, condition);
}


public void singleQuoteString(String val,StringBuilder buf){
    buf.append('\'');
    String s0 = replace(val, "'", "''");
    buf.append(s0);
    buf.append('\'');
}


public RuntimeException newElementNotFoundException(int category,IdentifierNode identifierNode){
    String type;
    switch(category) {
        case Category.Member:
            return MondrianResource.instance().MemberNotFound.ex(identifierNode.toString());
        case Category.Unknown:
            type = "Element";
            break;
        default:
            type = Category.instance().getDescription(category);
    }
    return newError(type + " '" + identifierNode + "' not found");
}


public int lastIndexOf(Object o){
    if (t2.equals(o)) {
        return 2;
    }
    if (t1.equals(o)) {
        return 1;
    }
    if (t0.equals(o)) {
        return 0;
    }
    return -1;
}


public String replaceProperties(String text,Map<String,String> env){
    // As of JDK 1.5, cannot use StringBuilder - appendReplacement requires
    // the antediluvian StringBuffer.
    StringBuffer buf = new StringBuffer(text.length() + 200);
    Pattern pattern = Pattern.compile("\\$\\{([^${}]+)\\}");
    Matcher matcher = pattern.matcher(text);
    while (matcher.find()) {
        String varName = matcher.group(1);
        String varValue = env.get(varName);
        if (varValue != null) {
            matcher.appendReplacement(buf, varValue);
        } else {
            matcher.appendReplacement(buf, "\\${$1}");
        }
    }
    matcher.appendTail(buf);
    return buf.toString();
}


public SchemaReader getSchemaReader(){
    throw new UnsupportedOperationException();
}


@SuppressWarnings({ "CloneDoesntCallSuperClone" })
@Override
public PropertyList clone(){
    return new PropertyList(new ArrayList<Pair<String, String>>(list));
}


public String unparse(Query query){
    StringWriter sw = new StringWriter();
    PrintWriter pw = new QueryPrintWriter(sw);
    query.unparse(pw);
    return sw.toString();
}


public Timer newTimer(String name,boolean isDaemon){
    return compatible.newTimer(name, isDaemon);
}


public BitSet bitSetBetween(int fromIndex,int toIndex){
    final BitSet bitSet = new BitSet();
    if (toIndex > fromIndex) {
        // Avoid http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6222207
        // "BitSet internal invariants may be violated"
        bitSet.set(fromIndex, toIndex);
    }
    return bitSet;
}


public T[] append(T[] a,T o){
    T[] a2 = Util.copyOf(a, a.length + 1);
    a2[a.length] = o;
    return a2;
}


public String getErrorMessage(Throwable err,boolean prependClassName){
    String errMsg = err.getMessage();
    if ((errMsg == null) || (err instanceof RuntimeException)) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        err.printStackTrace(pw);
        return sw.toString();
    } else {
        return (prependClassName) ? err.getClass().getName() + ": " + errMsg : errMsg;
    }
}


}