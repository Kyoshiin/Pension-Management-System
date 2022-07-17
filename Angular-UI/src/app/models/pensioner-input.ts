export class PensionerInput {
    constructor(
        public name: String,
        public dateOfBirth: Date,
        public pan: String,
        public aadharNumber: String,
        public pensionType: String,
    ) { }
}
