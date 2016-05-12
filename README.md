# android-mvp-architecture
## Libraries
In this sample project includes some of useful libraries for Android, such as:
- [Retrofit](http://square.github.io/retrofit/): A type-safe REST client for Android which intelligently maps an API into a client interface using annotations.
- [Picasso](http://square.github.io/picasso/): A powerful image downloading and caching library for Android.
- [ButterKnife](http://jakewharton.github.io/butterknife/): Using Java annotations, makes Android development better by simplifying common tasks.
- [LeakCanary](http://github.com/square/leakcanary): Catch memory leaks in your apps
- [Material Dialogs](http://github.com/afollestad/material-dialogs): A beautiful, easy-to-use, and customizable dialogs API, enabling you to use Material designed dialogs down to API 8.
- [CircleImageView](http://github.com/hdodenhof/CircleImageView): A circular ImageView for Android

## The Clean Architecture
![clean architecture](https://blog.8thlight.com/assets/posts/2012-08-13-the-clean-architecture/CleanArchitecture-81565aba46f035911a5018e77a0f2d4e.jpg)

Before we go through to learn this architecture, I hope you've understand about the **Clean Architecture** (from [Uncle Bob](https://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html), [Dario Miličić](https://medium.com/@dmilicic/a-detailed-guide-on-developing-android-apps-using-the-clean-architecture-pattern-d38d71e94029#.8b4imw33h), and [Android10](http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/)). This architecture is the simple combination from those references. So, this architecture makes your code:
- **Independent of Frameworks.**
- **Testable**.
- **Independent of UI.**
- **Independent of Database.**
- **Independent of any external agency.**

## Sample API
This project using sample API from Mashape Free API: [Poetry DB](https://market.mashape.com/thundercomb/poetry-db#)
![screenshoot](https://cloud.githubusercontent.com/assets/5654040/15206486/cde22fc2-1849-11e6-95eb-8bbf16828da0.png)

## Links, Resources and References
1. https://github.com/codepath/android_guides/wiki/Must-Have-Libraries
2. https://github.com/futurice/android-best-practices
3. https://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html
4. https://medium.com/@dmilicic/a-detailed-guide-on-developing-android-apps-using-the-clean-architecture-pattern-d38d71e94029#.8b4imw33h
5. http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/
6. https://market.mashape.com/thundercomb/poetry-db#
