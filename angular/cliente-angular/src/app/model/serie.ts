import { Produtora } from "./produtora";
import { Genero } from "./genero";

export class Serie{
    id:number;
    nome:string;
    resumo:string;
    nota:number;
    dataEstreia:Date;
    dataEncerramento:Date;
    produtora:Produtora;
    genero:Genero;
    imagem:string;
}