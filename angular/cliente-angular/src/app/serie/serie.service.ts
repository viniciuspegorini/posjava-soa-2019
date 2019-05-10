import { Injectable } from '@angular/core';
import { Serie } from '../model/serie';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { CrudService } from '../generic/crud.service';
import { Observable } from 'rxjs';
import { Page } from '../generic/page';

@Injectable()
export class SerieService extends CrudService<Serie, number> {

  constructor(http: HttpClient) {
    super(environment.api_serie + '/serie', http);
  }

  findSearchPageable(filter: string, page: number, size: number, order?: string, asc?: boolean): Observable<Page<Serie>> {
    let url = `${this.getUrl()}/search?filter=${filter}&page=${page}&size=${size}`;
    if (order) {
      url += `&order=${order}`;
    }
    if (asc !== undefined) {
      url += `&asc=${asc}`;
    }
    return this.http.get<Page<Serie>>(url);
  }
}
