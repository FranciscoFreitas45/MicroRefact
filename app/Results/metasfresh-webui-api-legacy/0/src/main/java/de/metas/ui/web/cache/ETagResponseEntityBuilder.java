package de.metas.ui.web.cache;
 import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import org.adempiere.util.lang.ExtendedMemorizingSupplier;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import com.google.common.collect.ImmutableMap;
import de.metas.i18n.ADLanguageList;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions;
import de.metas.ui.web.window.datatypes.json.JSONDocumentOptions;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import lombok.NonNull;
public class ETagResponseEntityBuilder {

 private  WebRequest request;

 private  T etagAware;

 private  Supplier<R> result;

 private  Supplier<JSONOptions> _jsonOptionsSupplier;

 private  Supplier<JSONDocumentLayoutOptions> _jsonLayoutOptionsSupplier;

 private  Supplier<JSONDocumentOptions> _jsonDocumentOptionsSupplier;

 private  int cacheMaxAgeSec;

 private  boolean includeLanguageInETag;


public ETagResponseEntityBuilder<T,R> cacheMaxAge(int cacheMaxAgeSec){
    this.cacheMaxAgeSec = cacheMaxAgeSec >= 0 ? cacheMaxAgeSec : 0;
    return this;
}


public JSONDocumentLayoutOptions getJSONLayoutOptions(){
    final Supplier<JSONDocumentLayoutOptions> jsonLayoutOptionsSupplier = this._jsonLayoutOptionsSupplier;
    if (jsonLayoutOptionsSupplier == null) {
        throw new IllegalStateException("jsonLayoutOptions suppliere not configured");
    }
    final JSONDocumentLayoutOptions jsonLayoutOptions = jsonLayoutOptionsSupplier.get();
    if (jsonLayoutOptions == null) {
        throw new IllegalStateException("jsonLayoutOptions not configured");
    }
    return jsonLayoutOptions;
}


public ETagResponseEntityBuilder<T,R> jsonOptions(Supplier<JSONOptions> jsonOptions){
    this._jsonOptionsSupplier = ExtendedMemorizingSupplier.of(jsonOptions);
    this._jsonLayoutOptionsSupplier = null;
    this._jsonDocumentOptionsSupplier = null;
    return this;
}


public ETagResponseEntityBuilder<T,R> jsonLayoutOptions(Supplier<JSONDocumentLayoutOptions> jsonLayoutOptions){
    this._jsonOptionsSupplier = null;
    this._jsonLayoutOptionsSupplier = ExtendedMemorizingSupplier.of(jsonLayoutOptions);
    this._jsonDocumentOptionsSupplier = null;
    return this;
}


public String getAdLanguage(){
    if (_jsonOptionsSupplier != null) {
        return getJSONOptions().getAdLanguage();
    } else if (_jsonLayoutOptionsSupplier != null) {
        return getJSONLayoutOptions().getAdLanguage();
    }
    if (_jsonDocumentOptionsSupplier != null) {
        return getJSONDocumentOptions().getAdLanguage();
    } else {
        throw new IllegalStateException("no json options configured");
    }
// TODO
}


public ResponseEntity<BodyType> toResponseEntity(BiFunction<ResponseEntity.BodyBuilder,R,ResponseEntity<BodyType>> toJsonMapper){
    // Check ETag
    final String etag = getETag().toETagString();
    if (request.checkNotModified(etag)) {
        // Response: 304 Not Modified
        return newResponse(HttpStatus.NOT_MODIFIED, etag).build();
    }
    // Get the result and convert it to JSON
    final R result = this.result.get();
    final ResponseEntity.BodyBuilder newResponse = newResponse(HttpStatus.OK, etag);
    return toJsonMapper.apply(newResponse, result);
}


public JSONOptions getJSONOptions(){
    final Supplier<JSONOptions> jsonOptionsSupplier = this._jsonOptionsSupplier;
    if (jsonOptionsSupplier == null) {
        throw new IllegalStateException("jsonOptions suppliere not configured");
    }
    final JSONOptions jsonOptions = jsonOptionsSupplier.get();
    if (jsonOptions == null) {
        throw new IllegalStateException("jsonOptions not configured");
    }
    return jsonOptions;
}


public ETagResponseEntityBuilder<T,R> includeLanguageInETag(boolean includeLanguageInETag){
    this.includeLanguageInETag = includeLanguageInETag;
    return this;
}


public ResponseEntity<JSONType> toJson(BiFunction<R,JSONOptions,JSONType> toJsonMapper){
    return toResponseEntity((responseBuilder, result) -> responseBuilder.body(toJsonMapper.apply(result, getJSONOptions())));
}


public ETag getETag(){
    ETag etag = etagAware.getETag();
    if (includeLanguageInETag) {
        final String adLanguage = getAdLanguage();
        etag = etag.overridingAttributes(ImmutableMap.of("lang", adLanguage));
    }
    return etag;
}


public JSONDocumentOptions getJSONDocumentOptions(){
    final Supplier<JSONDocumentOptions> jsonDocumentOptionsSupplier = this._jsonDocumentOptionsSupplier;
    if (jsonDocumentOptionsSupplier == null) {
        throw new IllegalStateException("jsonDocumentOptions suppliere not configured");
    }
    final JSONDocumentOptions jsonDocumentOptions = jsonDocumentOptionsSupplier.get();
    if (jsonDocumentOptions == null) {
        throw new IllegalStateException("jsonDocumentOptions not configured");
    }
    return jsonDocumentOptions;
}


public ResponseEntity<JSONType> toDocumentJson(BiFunction<R,JSONDocumentOptions,JSONType> toJsonMapper){
    return toResponseEntity((responseBuilder, result) -> responseBuilder.body(toJsonMapper.apply(result, getJSONDocumentOptions())));
}


public ETagResponseEntityBuilder<T,R> jsonDocumentOptions(Supplier<JSONDocumentOptions> jsonDocumentOptions){
    this._jsonOptionsSupplier = null;
    this._jsonLayoutOptionsSupplier = null;
    this._jsonDocumentOptionsSupplier = ExtendedMemorizingSupplier.of(jsonDocumentOptions);
    return this;
}


public ETagResponseEntityBuilder<T,R2> map(Function<R,R2> resultMapper){
    final Supplier<R> result = this.result;
    final Supplier<R2> newResult = () -> resultMapper.apply(result.get());
    return new ETagResponseEntityBuilder<>(request, etagAware, newResult).includeLanguageInETag(includeLanguageInETag).cacheMaxAge(this.cacheMaxAgeSec);
}


public ETagResponseEntityBuilder<T,T> ofETagAware(WebRequest request,T etagAware){
    return new ETagResponseEntityBuilder<>(request, etagAware, () -> etagAware);
}


public ResponseEntity<JSONType> toLayoutJson(BiFunction<R,JSONDocumentLayoutOptions,JSONType> toJsonMapper){
    return toResponseEntity((responseBuilder, result) -> responseBuilder.body(toJsonMapper.apply(result, getJSONLayoutOptions())));
}


public ResponseEntity.BodyBuilder newResponse(HttpStatus status,String etag){
    ResponseEntity.BodyBuilder response = ResponseEntity.status(status).eTag(etag).cacheControl(CacheControl.maxAge(cacheMaxAgeSec, TimeUnit.SECONDS));
    final String adLanguage = getAdLanguage();
    if (adLanguage != null && !adLanguage.isEmpty()) {
        final String contentLanguage = ADLanguageList.toHttpLanguageTag(adLanguage);
        response.header(HttpHeaders.CONTENT_LANGUAGE, contentLanguage);
        // advice browser to include ACCEPT_LANGUAGE in their caching key
        response.header(HttpHeaders.VARY, HttpHeaders.ACCEPT_LANGUAGE);
    }
    return response;
}


}