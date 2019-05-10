import { Component, OnInit } from '@angular/core';
import { Produtora } from '../model/produtora';
import { ProdutoraService } from './produtora.service';

@Component({
  selector: 'app-produtora',
  templateUrl: './produtora.component.html',
  styleUrls: ['./produtora.component.css']
})
export class ProdutoraComponent implements OnInit {

  produtoras: Produtora[];
  cols: any[];

  constructor(private produtoraService: ProdutoraService) { }

  ngOnInit() {
    this.findAll();
    this.cols = [
      { field: 'id', header: 'Código' },
      { field: 'nome', header: 'Nome' }
    ];
  }

  findAll() {
    this.produtoraService.findAll().subscribe(
      e => this.produtoras = e);
  }

}
