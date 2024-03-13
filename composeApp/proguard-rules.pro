# Google Credentials
-if class androidx.credentials.CredentialManager
-keep class androidx.credentials.playservices.** {
  *;
}

-dontwarn org.conscrypt.**
-dontwarn org.bouncycastle.**
-dontwarn org.openjsse.**
-dontwarn org.slf4j.**