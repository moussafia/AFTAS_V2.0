import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthModule } from './auth/auth.module';
import { LayoutModuleModule } from './layout-module/layout-module.module';
import { JwtInterceptor } from './interceptor/jwt.interceptor';
import { authInterceptor } from './interceptor/auth.interceptor';

@NgModule({
  declarations: [
    AppComponent,

  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    AuthModule,
    LayoutModuleModule
  ],
  exports:[
    RouterModule,
    AppRoutingModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true}
    //{provide: HTTP_INTERCEPTORS, useClass: authInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
