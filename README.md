`Optional` – Java 8 Optional-like APIs for everyone
---

[![Android Arsenal][1]][2] [![JitPack][3]][4]

![You get to use Optional! And you! And you there!](https://i.imgflip.com/1g8eyz.jpg)


### Differences compared to other Optional-like APIs

|                                  |             `Optional`            |               Java 8's Optional              |  Guava's Optional  | Optio |
|----------------------------------|:---------------------------------:|:--------------------------------------------:|:------------------:|:-----:|
| Required JDK version             |                1.7                |                      1.8                     |         1.6        |  1.7  |
| Android-compatible               |                Yes                |        Only for API 24 (Nougat) and up       |         Yes        |  Yes  |
| Serializable                     |                Yes                |                       No                     |         Yes        |   No  |
| Support for primitive types      | Yes, all of them except `boolean` |     Only for `int`, `double`, and `long`     |        None        |  None |
| Support for functional use-cases |             Partially             | Yes, including `filter()`, `flatMap()`, etc. |        None        |  None |


### Unique to `Optional`

- `orNull()` instead of `orElse(null)`.
- `ifPresentOrElse(Consumer, Function)`.
- `OptionalByte`, `OptionalShort`, `OptionalFloat`, and `OptionalChar`.



### Including `Optional` to your project

Include `Optional` to your Gradle project by adding it as a dependency in your `build.gradle` like so:

```
   repositories {
       maven { url "https://jitpack.io" }
   }
   
   dependencies {
       compile 'com.hadisatrio:Optional:v1.0.1'
   }
```


### Contributions 

Any kind of contributions will be appreciated. PR away!


### License

`Optional` is published under the [MIT license](https://opensource.org/licenses/MIT).

---

_**p.s.**, Please let me know if you're using `Optional` in your projects. Drop an email at 
hi[you-know-what-to-put-here]hadisatrio.com._ ;)

[1]: https://img.shields.io/badge/Android%20Arsenal-Optional-brightgreen.svg?style=flat-square
[2]: https://android-arsenal.com/details/1/4906
[3]: https://jitpack.io/v/MrHadiSatrio/Optional.svg?style=flat-square
[4]: https://jitpack.io/#MrHadiSatrio/Optional
