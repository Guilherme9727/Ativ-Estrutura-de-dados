import { Routes } from '@angular/router';
import {Formulario} from './formulario/formulario';
import path from 'path';

export const routes: Routes = [
    {path:"", component:Formulario},
    {path:"cadastro", component: Formulario}
];