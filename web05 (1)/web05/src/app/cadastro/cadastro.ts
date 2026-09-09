import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  imports: [FormsModule],
  selector: 'app-cadastro',
  styleUrl: './cadastro.css',
  templateUrl: './cadastro.html',
})
export class Cadastro {
  nome = '';
  email = '';
  telefone = '';
  senha = '';
  confirmacaoSenha = '';
  enviado = false;

  cadastrar(formulario: NgForm): void {
    this.enviado = false;
    if (formulario.invalid || this.senha !== this.confirmacaoSenha) {
      formulario.control.markAllAsTouched();
      return;
    }

    // Demonstração de interface: integrar uma API aqui para criar a conta.
    // Não armazenamos dados pessoais ou senhas no navegador.
    formulario.resetForm();
    this.enviado = true;
  }

  limpar(formulario: NgForm): void {
    formulario.resetForm();
    this.enviado = false;
  }
}
