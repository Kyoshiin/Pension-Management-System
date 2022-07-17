import {
  HttpEvent, HttpHandler, HttpInterceptor, HttpRequest
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // add authorization header with jwt token if available
    const token = this.authService.getToken();
    
    //Header in d form: Bearer <token>
    if (token) {
      var parsedToken = JSON.parse(token); // json obj {token:key}
      request = request.clone({
        setHeaders: {
          Authorization: "Bearer " + parsedToken.token
        }
      });
    }
    return next.handle(request);

  }
}
