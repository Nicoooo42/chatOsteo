import { Routes } from '@angular/router';
import { AppAuthGuard } from './auth/app.authguard';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  {
    path: 'chat',
    canActivate: [AppAuthGuard],
    loadComponent: () => import('./pages/home/chat/chat.page').then( m => m.ChatPage)
  },
  {
    path: 'home',
    loadComponent: () => import('./pages/home/home.page').then( m => m.HomePage)
  }
];
