import { APP_INITIALIZER, enableProdMode, importProvidersFrom } from '@angular/core';
import { bootstrapApplication } from '@angular/platform-browser';
import { RouteReuseStrategy, provideRouter } from '@angular/router';
import { IonicModule, IonicRouteStrategy } from '@ionic/angular';

import { routes } from './app/app.routes';
import { AppComponent } from './app/app.component';
import { environment } from './environments/environment';
import { AppAuthGuard } from './app/auth/app.authguard';
import { AuthService } from './app/auth/auth.service';
import { KeycloakService } from 'keycloak-angular';
import { initializeKeycloak } from './app/auth/keycloak-init.factory';

if (environment.production) {
  enableProdMode();
}

bootstrapApplication(AppComponent, {
  providers: [     AuthService,
    KeycloakService,
    AppAuthGuard,
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService],
    },
    { provide: RouteReuseStrategy, useClass: IonicRouteStrategy },
    importProvidersFrom(IonicModule.forRoot({})),
    provideRouter(routes),
  ],
});
