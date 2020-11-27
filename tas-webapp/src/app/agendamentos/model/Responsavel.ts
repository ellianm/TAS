import { Pessoa } from 'src/app/_services/cliente.service';

export class ResponsavelEntity extends Pessoa {
    matricula: string;
    constructor() {
        super();
    }
}