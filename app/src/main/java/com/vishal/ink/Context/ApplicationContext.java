package com.vishal.ink.Context;

import android.content.Context;

public class ApplicationContext {
     private static ApplicationContext instance;
     private Context context;

     // Private constructor to prevent instantiation
     private ApplicationContext() {}

     // Get the singleton instance of the ApplicationContext
     public static synchronized ApplicationContext getInstance() {
          if (instance == null) {
               instance = new ApplicationContext();
          }
          return instance;
     }

     // Set the application context
     public void setContext(Context context) {
          this.context = context;
     }

     // Get the application context
     public Context getContext() {
          return context;
     }
}
