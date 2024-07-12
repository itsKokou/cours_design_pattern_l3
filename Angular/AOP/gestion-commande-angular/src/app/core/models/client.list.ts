export interface ClientList{
    id?: number,
    nomComplet : string,
    telephone: string,
    quartier: string,
    villa: string,
    numVilla: string
}

export interface ClientCreate{
    nomComplet : string,
    telephone: string,
    quartier: string,
    ville: string,
    numVilla: string
}

export interface ClientFormCommande{
    id: number,
    nomComplet : string,
    telephone: string,
    adresse: string
}