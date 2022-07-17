export class ProcessPensionInput {

    constructor(
        public aadharNumber: string,
        public pensionAmount: Number,
        public bankCharge: Number,
    ) { }
}
