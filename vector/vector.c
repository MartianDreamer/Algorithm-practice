struct Vector
{
    /* data */
    int *arr;
    int size;
    int capacity;
};

void insert(int number, struct Vector *vector)
{
    if (vector->size >= vector->capacity)
    {
        int *tempArr;
        tempArr = malloc(sizeof(int) * vector->capacity * 2);
        for (int i = 0; i < vector->size; i++)
        {
            tempArr[i] = vector->arr[i];
        }
        tempArr[vector->size] = number;
        vector->arr = tempArr;
        vector->size++;
        return;
    }
    vector->arr[vector->size] = number;
    vector->size++;
    return;
}

int main()
{
    int *arr;
    arr = malloc(sizeof(int));
    struct Vector vector = {
        arr,
        0,
    };
    insert(10, &vector);
    insert(9, &vector);
    insert(8, &vector);
    insert(7, &vector);
    for (int i = 0; i < vector.size; i++)
    {
        printf("%d \n", vector.arr[i]);
    }
}