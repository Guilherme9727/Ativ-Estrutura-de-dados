import { Routes } from '@angular/router';
import { Formulario } from './formulario/formulario';


export const routes: Routes = [
    {path:"", component:Formulario},
    {path:"cadastro", component:Formulario}
];
