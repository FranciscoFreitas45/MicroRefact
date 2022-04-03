package pl.szymanski.sharelibrary.services.adapters;
 import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.szymanski.sharelibrary.converters.RequestConverter;
import pl.szymanski.sharelibrary.entity;
import pl.szymanski.sharelibrary.enums.BookCondition;
import pl.szymanski.sharelibrary.enums.BookStatus;
import pl.szymanski.sharelibrary.enums.ExchangeStatus;
import pl.szymanski.sharelibrary.exceptions.ExceptionMessages;
import pl.szymanski.sharelibrary.exceptions.exchanges.ExchangeNotExist;
import pl.szymanski.sharelibrary.repositories.ports.CategoryRepository;
import pl.szymanski.sharelibrary.repositories.ports.CoordinatesRepository;
import pl.szymanski.sharelibrary.repositories.ports.ExchangeRepository;
import pl.szymanski.sharelibrary.repositories.ports.LanguageRepository;
import pl.szymanski.sharelibrary.requests.AddExchangeRequest;
import pl.szymanski.sharelibrary.requests.ExecuteExchangeRequest;
import pl.szymanski.sharelibrary.response.ExchangeResponse;
import pl.szymanski.sharelibrary.services.ports.BookService;
import pl.szymanski.sharelibrary.services.ports.ExchangeService;
import pl.szymanski.sharelibrary.services.ports.UserService;
import java.util;
import java.util.stream.Collectors;
import java.lang.Math;
@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService{

 private  ExchangeRepository exchangeRepository;

 private  BookService bookService;

 private  UserService userService;

 private  CoordinatesRepository coordinatesRepository;

 private  CategoryRepository categoryRepository;

 private  LanguageRepository languageRepository;


@Override
public Exchange saveExchange(AddExchangeRequest addExchangeRequest){
    Exchange exchange = RequestConverter.addExchangeRequestToExchange(addExchangeRequest);
    exchange.setBook(bookService.findBookById(addExchangeRequest.getBookId()));
    exchange.setUser(userService.getUserById(addExchangeRequest.getUserId()));
    exchange.setCoordinates(checkIfCoordinatesExist(exchange.getCoordinates()));
    exchange.setExchangeStatus(ExchangeStatus.STARTED);
    exchange.getUser().getBooks().forEach(it -> {
        if (it.getBook().getId().equals(exchange.getBook().getId())) {
            it.setStatus(BookStatus.SHARED);
        }
    });
    return exchangeRepository.saveExchange(exchange);
}


public List<Exchange> getUserExchange(Long userId){
    return exchangeRepository.getAll().stream().filter(it -> it.getUser().getId().equals(userId)).collect(Collectors.toList());
}


public List<Exchange> filterByQuery(List<Exchange> exchanges,String query){
    List<String> queries = Arrays.asList(query.split(" "));
    Set<Exchange> result = filterByTitle(exchanges, query);
    result.addAll(filterByAuthors(exchanges, query));
    queries.forEach(q -> {
        result.addAll(filterByTitle(exchanges, q));
        result.addAll(filterByAuthors(exchanges, q));
    });
    return new LinkedList<>(result);
}


public Set<Exchange> filterByAuthors(List<Exchange> exchanges,String query){
    Set<Exchange> result = new HashSet<>();
    exchanges.forEach(exchange -> {
        if (exchange.getBook().getAuthors().stream().anyMatch(author -> author.getName().toLowerCase().contains(query.toLowerCase()) || author.getSurname().toLowerCase().contains(query.toLowerCase()))) {
            result.add(exchange);
        }
    });
    return result;
}


@Override
public List<Requirement> getRequirements(Long exchangeId){
    return getExchangeById(exchangeId).getRequirements().stream().filter(Requirement::isActual).collect(Collectors.toList());
}


public List<Exchange> getExchangeWhichContainsAllCategories(List<Exchange> exchanges,List<Category> categories){
    return exchanges.stream().filter(exchange -> exchange.getBook().getCategories().containsAll(categories)).collect(Collectors.toList());
}


@Override
public List<Exchange> getExchangesByCoordinatesAndRadius(double latitude,double longitude,double radius){
    double radiusInM = radius * 1000.0;
    double LAT_MIN = -PI / 2;
    double LAT_MAX = PI / 2;
    double LON_MIN = -PI;
    double LON_MAX = PI;
    double latMin, latMax, lonMin, lonMax;
    double earthRadiusInKM = 6371.0;
    double lat = Math.toRadians(latitude);
    double lon = Math.toRadians(longitude);
    double angularRadius = radius / earthRadiusInKM;
    latMin = lat - angularRadius;
    latMax = lat + angularRadius;
    if (latMin > LAT_MIN && latMax < LAT_MAX) {
        double dLon = asin(sin(angularRadius) / cos(lat));
        lonMin = lon - dLon;
        if (lonMin < LON_MIN)
            lonMin += 2 * PI;
        lonMax = lon + dLon;
        if (lonMax > LON_MAX)
            lonMax -= 2 * PI;
    } else if (latMin < LAT_MIN) {
        latMax = LAT_MAX;
        lonMin = LON_MIN;
        lonMax = LON_MAX;
    } else {
        latMin = LAT_MIN;
        lonMin = LON_MIN;
        lonMax = LON_MAX;
    }
    return exchangeRepository.getExchangeByBoundingCoordinates(toDegrees(latMin), toDegrees(latMax), toDegrees(lonMin), toDegrees(lonMax)).stream().filter(it -> countDistanceBetweenPoints(latitude, longitude, it.getCoordinates().getLatitude(), it.getCoordinates().getLongitude()) <= radiusInM).collect(Collectors.toList());
}


@Override
public List<Exchange> getExchangesByUserId(Long userId){
    return exchangeRepository.getAll().stream().filter(it -> it.getExchangeStatus() != ExchangeStatus.FINISHED && it.getUser().getId().equals(userId)).collect(Collectors.toList());
}


public List<Category> getCategoryListFromNameList(List<String> categories){
    List<Category> categoryList = new LinkedList<>();
    categories.forEach(it -> categoryRepository.findByName(it).ifPresent(categoryList::add));
    return categoryList;
}


public List<Exchange> filterByLanguage(List<Exchange> exchanges,Language language){
    return exchanges.stream().filter(it -> it.getBook().getLanguage().getId().equals(language.getId())).collect(Collectors.toList());
}


@Override
public Exchange getExchangeById(Long id){
    return exchangeRepository.getExchangeById(id).orElseThrow(() -> new ExchangeNotExist(id));
}


public Set<Exchange> filterByTitle(List<Exchange> exchanges,String query){
    Set<Exchange> result = new HashSet<>();
    exchanges.forEach(it -> {
        if (it.getBook().getTitle().toLowerCase().contains(query.toLowerCase())) {
            result.add(it);
        }
    });
    return result;
}


@Override
@Transactional
public void finishExchange(Long exchangeId){
    Exchange exchange = exchangeRepository.getExchangeById(exchangeId).orElseThrow(() -> new ExchangeNotExist(exchangeId));
    exchange.setExchangeStatus(ExchangeStatus.FINISHED);
    exchange.getUser().getBooks().forEach(it -> {
        if (it.getBook().getId().equals(exchange.getBook().getId())) {
            it.setStatus(BookStatus.AT_OWNER);
            it.setAtUser(null);
        }
    });
    if (exchange.getForBook() != null) {
        exchange.getWithUser().getBooks().forEach(it -> {
            if (it.getBook().getId().equals(exchange.getForBook().getId())) {
                it.setStatus(BookStatus.AT_OWNER);
                it.setAtUser(null);
            }
        });
    }
    exchangeRepository.saveExchange(exchange);
}


public Coordinates checkIfCoordinatesExist(Coordinates coordinates){
    return coordinatesRepository.findByLatitudeAndLongitude(coordinates.getLatitude(), coordinates.getLongitude()).orElseGet(() -> coordinatesRepository.saveCoordinates(coordinates));
}


@Override
public List<ExchangeResponse> filter(Double latitude,Double longitude,Double radius,List<String> categories,String query,Integer languageId,List<Integer> conditions){
    if (latitude == null || longitude == null)
        throw new IllegalArgumentException(ExceptionMessages.COORDINATES_CANNOT_BE_NULL);
    List<Exchange> exchanges = getExchangesByCoordinatesAndRadius(latitude, longitude, radius).stream().filter(it -> it.getExchangeStatus().equals(ExchangeStatus.STARTED)).collect(Collectors.toList());
    if (conditions != null) {
        List<BookCondition> bookConditions = Arrays.stream(BookCondition.values()).filter(it -> conditions.contains(it.ordinal())).collect(Collectors.toList());
        exchanges = filterByBookCondition(exchanges, bookConditions);
    }
    if (languageId != null) {
        Optional<Language> language = languageRepository.getLanguageById(languageId);
        if (language.isPresent()) {
            exchanges = filterByLanguage(exchanges, language.get());
        }
    }
    if (categories != null) {
        List<Category> newCategories = getCategoryListFromNameList(categories);
        exchanges = filterByCategory(exchanges, newCategories);
    }
    if (query != null && !query.isBlank()) {
        exchanges = filterByQuery(exchanges, query);
    }
    return exchanges.stream().map(it -> ExchangeResponse.of(it, countDistanceBetweenPoints(latitude, longitude, it.getCoordinates().getLatitude(), it.getCoordinates().getLongitude()))).collect(Collectors.toList());
}


public List<Exchange> filterByBookCondition(List<Exchange> exchanges,List<BookCondition> bookCondition){
    return exchanges.stream().filter(it -> bookCondition.contains(it.getBook().getCondition())).collect(Collectors.toList());
}


public LinkedList<Exchange> filterByCategory(List<Exchange> exchanges,List<Category> categories){
    return new LinkedList<>(getExchangeWhichContainsAllCategories(exchanges, categories));
}


@Override
@Transactional
public Exchange executeExchange(ExecuteExchangeRequest executeExchangeRequest){
    Exchange exchange = getExchangeById(executeExchangeRequest.getExchangeId());
    User withUser = userService.getUserById(executeExchangeRequest.getWithUserId());
    if (executeExchangeRequest.getForBookId() != null) {
        exchange.setForBook(bookService.findBookById(executeExchangeRequest.getForBookId()));
        withUser = changeBookStatusAndAtUser(executeExchangeRequest.getWithUserId(), exchange.getForBook().getId(), BookStatus.EXCHANGED, exchange.getUser().getId());
    }
    User owner = changeBookStatusAndAtUser(exchange.getUser().getId(), exchange.getBook().getId(), BookStatus.EXCHANGED, executeExchangeRequest.getWithUserId());
    exchange.setExchangeStatus(ExchangeStatus.DURING);
    exchange.setWithUser(withUser);
    exchange.setUser(owner);
    exchange.getRequirements().forEach(requirement -> requirement.setActual(false));
    Exchange savedExchange = exchangeRepository.saveExchange(exchange);
    if (savedExchange != null && savedExchange.getForBook() != null) {
        getUserExchange(withUser.getId()).stream().filter(it -> it.getExchangeStatus().equals(ExchangeStatus.STARTED) && it.getBook().getId().equals(savedExchange.getForBook().getId())).findFirst().ifPresent(it -> {
            it.setExchangeStatus(ExchangeStatus.DURING);
            exchangeRepository.saveExchange(it);
        });
    }
    return savedExchange;
}


@Override
public List<Exchange> getExchangesLinkedByUser(Long userId){
    return exchangeRepository.getExchangesLinkedWithUser(userId);
}


public List<Exchange> getExchangesByWithUserId(Long userId){
    List<Exchange> exchanges = exchangeRepository.getExchangeByStatus(ExchangeStatus.DURING);
    return exchanges.stream().filter(it -> it.getWithUser() != null && it.getWithUser().getId().equals(userId)).collect(Collectors.toList());
}


@Override
public double countDistanceBetweenPoints(double lat1,double lon1,double lat2,double lon2){
    double radiusInM = 6371000.0;
    lat1 = Math.toRadians(lat1);
    lat2 = Math.toRadians(lat2);
    lon1 = Math.toRadians(lon1);
    lon2 = Math.toRadians(lon2);
    return 2 * radiusInM * Math.asin(Math.sqrt(Math.pow(Math.sin((lat2 - lat1) / 2), 2) + cos(lat1) * cos(lat2) * Math.pow(Math.sin((lon2 - lon1) / 2), 2)));
}


public User changeUserBookStatus(Long userId,Long bookId,BookStatus newStatus){
    User user = userService.getUserById(userId);
    List<UserBook> userBooks = user.getBooks();
    userBooks.forEach(ub -> {
        if (ub.getBook().getId().equals(bookId)) {
            ub.setStatus(newStatus);
        }
    });
    user.setBooks(userBooks);
    return user;
}


public User changeBookStatusAndAtUser(Long userId,Long bookId,BookStatus newStatus,Long atUserId){
    User user = userService.getUserById(userId);
    User atUser = userService.getUserById(atUserId);
    List<UserBook> userBooks = user.getBooks();
    userBooks.forEach(ub -> {
        if (ub.getBook().getId().equals(bookId)) {
            ub.setStatus(newStatus);
            ub.setAtUser(atUser);
        }
    });
    user.setBooks(userBooks);
    return user;
}


}