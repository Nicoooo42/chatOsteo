import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  {
    path: 'chat',
    loadComponent: () => import('./pages/home/chat/chat.page').then( m => m.ChatPage)
  },
  {
    path: 'home',
    loadComponent: () => import('./pages/home/home.page').then( m => m.HomePage)
  },
  {
    path: 'chat',
    loadComponent: () => import('./pages/home/chat/chat.page').then( m => m.ChatPage)
  },
];
