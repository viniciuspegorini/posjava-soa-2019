import { Component, OnInit, ViewChild } from '@angular/core';
import { SerieService } from './serie.service';
import { ConfirmationService, LazyLoadEvent, Message } from 'primeng/api';
import { DataTable } from 'primeng/components/datatable/datatable';
import { Serie } from '../model/serie';
import { Genero } from '../model/genero';
import { Produtora } from '../model/produtora';
import { GeneroService } from '../genero/genero.service';
import { ProdutoraService } from '../produtora/produtora.service';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-serie',
  templateUrl: './serie.component.html',
  styleUrls: ['./serie.component.css']
})
export class SerieComponent implements OnInit {

  @ViewChild('dt') dataTable: DataTable;

  cols: any[];
  series: Serie[];
  totalRecords: number;
  maxRecords = 5;
  currentPage = 1;

  generos: Genero[];
  produtoras: Produtora[];
  produtorasFiltered: Produtora[];
  serieEdit: Serie = new Serie();

  showDialog = false;
  confirmDialog = false;
  msgs: Message[] = [];

  uploadedFiles: any[] = [];
  urlApi: string = environment.api_serie;
  today: number = Date.now();

  constructor(private serieService: SerieService,
    private confirmationService: ConfirmationService,
    private generoService: GeneroService,
    private produtoraService: ProdutoraService
    ) { }

  ngOnInit() {
    this.produtoraService.findAll().subscribe(e => this.produtoras = e);
    this.generoService.findAll().subscribe(e => this.generos = e);
    this.cols = [
      { field: 'id', header: 'Código' },
      { field: 'nome', header: 'Nome' },
      { field: 'nota', header: 'Nota' },
      { field: 'dataEstreia', header: 'Estréia' },
      { field: 'dataEncerramento', header: 'Encerramento' },
      { field: 'genero.nome', header: 'Gênero' },
      { field: 'produtora.nome', header: 'Produtora' },
    ];
  }

  findAllPaged(page: number, size: number) {
    this.serieService.count().subscribe(e => this.totalRecords = e);
    this.serieService.findPageable(page, size).subscribe(e => this.series = e.content);
  }

  findSearchPaged(filter: string, page: number, size: number) {
    this.serieService.count().subscribe(e => this.totalRecords = e);
    this.serieService.findSearchPageable(filter, page, size).subscribe(e => this.series = e.content);
  }

  loadLazy(event: LazyLoadEvent) {
    this.currentPage = event.first / event.rows;
    this.maxRecords = event.rows;
    if (event.globalFilter) {
      setTimeout(() => {
        this.findSearchPaged(event.globalFilter, this.currentPage, this.maxRecords);
      }, 250);
    } else {
      setTimeout(() => {
        this.findAllPaged(this.currentPage, this.maxRecords);
      }, 250);
    }
  }

  novo() {
    this.showDialog = true;
    this.serieEdit = new Serie();
    this.serieEdit.genero = this.generos[0];
  }

  editar(serie: Serie) {
    this.today = Date.now();
    this.serieEdit = Object.assign({}, serie);
    this.showDialog = true;
  }

  cancelar() {
    this.serieEdit = new Serie();
    this.showDialog = false;
  }

  salvar() {
    this.serieService.save(this.serieEdit).subscribe(e => {
      this.serieEdit = new Serie();
      this.dataTable.reset();
      this.showDialog = false;
      this.msgs = [{severity: 'success', summary: 'Confirmado',
                    detail: 'Registro salvo com sucesso'}];
    }, error => {
      this.msgs = [{severity: 'error', summary: 'Erro',
                    detail: 'Certifique-se de preencher todos os campos!'}];
    });

  }

  remover(serie: Serie) {
    this.confirmationService.confirm({
      message: 'Essa ação não pode ser desfeita',
      header: 'Deseja remover esse registro?',
      acceptLabel: 'Confirmar',
      rejectLabel: 'Cancelar',
      accept: () => {
        this.serieService.delete(serie.id).subscribe(() => {
          this.dataTable.reset();
          this.msgs = [{severity: 'success', summary: 'Confirmado',
            detail: 'Registro removido com sucesso!'}];

          }, error => {
            this.msgs = [{severity: 'error', summary: 'Errou',
              detail: 'Falha ao remover o registro!'}];
        });
      }
    });
  }

  search(event) {
    this.produtorasFiltered = this.produtoras.filter(
      p => p.nome.toLocaleLowerCase().includes(event.query.toLocaleLowerCase())
    );
  }

  onUpload(event) {
    for (const file of event.files) {
      this.uploadedFiles.push(file);
    }

    this.msgs = [{severity: 'info', summary: 'Arquivo salvo',
                  detail: 'Arquivo salvo com sucesso!'}];
    setTimeout(() => {
      this.dataTable.reset();
      this.showDialog = false;
      this.uploadedFiles = [];
    }, 500);
  }
}
