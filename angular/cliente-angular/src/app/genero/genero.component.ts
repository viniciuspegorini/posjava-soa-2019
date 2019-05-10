import { Component, OnInit } from '@angular/core';
import { GeneroService } from './genero.service';
import { Genero } from '../model/genero';
import { LoginService } from '../login/login.service';

@Component({
  selector: 'app-genero',
  templateUrl: './genero.component.html',
  styleUrls: ['./genero.component.css']
})
export class GeneroComponent implements OnInit {

  generos: Genero[];

  constructor(private generoService: GeneroService,
              private loginService: LoginService) { }

  ngOnInit() {
    this.findAll();
  }

  findAll() {
    this.generoService.findAll().subscribe(e => this.generos = e);
  }

  hasRole(role: string): boolean {
    return this.loginService.hasRole(role);
  }

  editar(genero: Genero) {

  }

  remover(genero: Genero) {

  }
}
