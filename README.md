# Android Template Project

![Android CI](https://github.com/lucaslrt/Simple-Android-Template/workflows/Android%20CI/badge.svg)


This is a simple android template project using 100% kotlin language, MVVM architecture, some of Jetpack lib features, Koin as injection dependency, Retrofit2 and Coroutines for API network, and some examples of Unit and Instrumented Tests using Mockito and JUnit4.

## Installation

Just clone the project via terminal using `git clone` or download the zipped project manually.

## Structure

<!-- ### Architecture -->

### UI
The project has one activity and three fragments:
- The first fragment has one item with model and repository not linked with retrofit;
- The second fragment has one item with model and repository linked with retrofit. It shows the current weather data that came from https://openweathermap.org/. If you want to test the content just insert your api key from Open Weather in <b>com/template/androidtemplateproject/data/api/AuthInterceptor.kt</b>:
``` kotlin
class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val url =
            req.url.newBuilder().addQueryParameter("APPID", "insert_api_key_here")
                .build()
        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}
```
- The third fragment is empty.

<!-- ## Usage -->

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

[MIT](https://choosealicense.com/licenses/mit/)