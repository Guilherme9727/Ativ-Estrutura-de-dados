import { Routes } from '@angular/router';
import { Formulario } from './formulario/formulario';
import { Principal } from './principal/principal';

export const routes: Routes = [
    {path:"", component: Principal},
    {path:"formulario", component:Formulario}
];
