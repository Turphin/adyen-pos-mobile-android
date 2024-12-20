# Adyen POS Mobile SDK for Android

## Introduction

This is the home page of the Adyen POS Mobile SDK for Android. 
Our mobile solution lets you accept in-person payments on an Android device, using a card reader that is connected via Bluetooth.

The repository contains the following sample applications: 

1. `app-default` is an example of automatic initialization of the SDK. 
 See [README.md](app-default/README.md).

2. `app-manual-initialization` is a more advanced example of manual initialization of the SDK. 
 See [README.md](app-manual-initialization/README.md)

## Artifactory Setup

To build the project you need to define some variables in your `local.properties` file locally.
```
# URL and credentials to the Maven repository
artifacts.url=<URL of the repository>
artifacts.username=<Your username>
artifacts.token=<Your access token to the repository>
# Credentials to the Adyen TEST environment
environment.apiKey=<Your API key>
environment.merchantAccount=<Your merchant account>
```

## Documentation
See our documentation on [docs.adyen.com](https://docs.adyen.com/point-of-sale/ipp-mobile/card-reader-android).
See SDK reference on [GitHub](https://adyen.github.io/adyen-pos-mobile-android).

## Support
If you have a feature request, or spotted a bug or a technical problem, please contact our support team.

## License
MIT license. For more information, see the LICENSE file.
