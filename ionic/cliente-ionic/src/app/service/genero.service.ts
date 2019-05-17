import { Injectable } from '@angular/core';
import { Genero } from '../model/genero';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { CrudService } from './crud.service';

@Injectable({
  providedIn: 'root'
})
export class GeneroService extends CrudService<Genero, number> {

  constructor(http: HttpClient) {
    super(environment.api_serie + '/genero', http);
  }

}
