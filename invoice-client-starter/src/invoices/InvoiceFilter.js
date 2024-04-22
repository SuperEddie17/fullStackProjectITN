import React from 'react';
import InputField from '../components/InputField';
import InputSelect from '../components/InputSelect';

const InvoiceFilter = (props) => {
    const { filter } = props;

    //funkce pri zmene hodnoty v inputu
    const handleChange = (e) => {
        props.handleChange(e);
    };
    //funkce pri odeslani formulare
    const handleSubmit = (e) => {
        props.handleSubmit(e);
    };

    return (
        <form onSubmit={handleSubmit}>
            <div className="row">
                <div className="col">
                    <InputSelect
                        name="buyerID"
                        items={props.personList}
                        handleChange={handleChange}
                        label="Odberatel"
                        prompt="nevybrán"
                        value={filter.buyerID}
                    />
                </div>

                <div className="col">
                    <InputSelect
                        name="sellerID"
                        items={props.personList}
                        handleChange={handleChange}
                        label="Dodavatel"
                        prompt="nevybrán"
                        value={filter.sellerID}
                    />
                </div>
            </div>
            <div className="row">
                <div className="col">
                    <InputField
                        type="text"
                        min="0"
                        name="Product"
                        handleChange={handleChange}
                        label="Produkt"
                        prompt="neuveden"
                        value={filter.product}
                    />
                </div>
                <div className="col">
                    <InputField
                        type="number"
                        min="1"
                        name="limit"
                        handleChange={handleChange}
                        label="Limit počtu faktur"
                        prompt="neuveden"
                        value={filter.limit}
                    />
                </div>
            </div>
            <div className="row">
                <div className="col">
                    <InputField
                        type="number"
                        min="1"
                        name="MinPrice"
                        handleChange={handleChange}
                        label="Cena od"
                        prompt="neuveden"
                        value={filter.minPrice}

                    />
                </div>
                <div className="col">
                    <InputField
                        type="number"
                        min="1"
                        name="MaxPrice"
                        handleChange={handleChange}
                        label="Cena do"
                        prompt="neuveden"
                        value={filter.maxPrice}
                    />
                </div>
            </div>

            <input
                type="submit"
                className="btn btn-secondary float-right mt-2"
                value={props.confirm}
            />

        </form>
    );
};

export default InvoiceFilter;